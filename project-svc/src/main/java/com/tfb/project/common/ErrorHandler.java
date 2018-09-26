package com.tfb.project.common;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class ErrorHandler {

    @ResponseBody
    @ExceptionHandler(value = Throwable.class)
    public Map<String, Object> defaultErrorHandler(HttpServletRequest req, HttpServletResponse res, Throwable e) throws Exception {
        log.error("服务器异常", e);
        Map<String, Object> result = new HashMap<>();
        result.put("message", e.getMessage());

        BindingResult bindingResult = getBindingResult(e);
        if (null != bindingResult) {
            List<FieldError> validError = bindingResult.getFieldErrors();
            StringBuilder sb = new StringBuilder();
            for (FieldError fieldError : validError) {
                String filed = fieldError.getField();
                Object val = fieldError.getRejectedValue();
                String errorMessage = fieldError.getDefaultMessage();
                if (sb.length() > 0) {
                    sb.append(" | ");
                }
                sb.append(filed).append("=").append(val).append(":").append(errorMessage);
            }
            result.put("message", sb.toString());
            res.setStatus(HttpStatus.BAD_REQUEST.value());
        } else {
            res.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        result.put("timestamp", LocalDateTime.now().toString());
        result.put("status", res.getStatus());
        return result;
    }

    private BindingResult getBindingResult(Throwable e){
        if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException validException = (MethodArgumentNotValidException) e;
            return validException.getBindingResult();
        } else if(e instanceof BindException){
            BindException validException = (BindException) e;
            return validException.getBindingResult();
        }
        return null;
    }
}


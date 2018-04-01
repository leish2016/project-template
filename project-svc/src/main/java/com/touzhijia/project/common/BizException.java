package com.touzhijia.project.common;

import org.springframework.http.HttpStatus;

/**
 * 业务异常
 */
public class BizException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private int status = HttpStatus.BAD_REQUEST.value();

    public BizException() {
        super();
    }

    public BizException(String message) {
        super(message);
    }

    public BizException(String message, int sc) {
        super(message);
        this.status = sc;
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }

    public BizException(String message, int sc, Throwable cause) {
        super(message, cause);
        this.status = sc;
    }

    public BizException(Throwable cause) {
        super(cause);
    }

    public BizException(int sc, Throwable cause) {
        super(cause);
        this.status = sc;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static void throwBizException(String message) {
        throw new BizException(message);
    }

    public static void throwBizException(String message,Throwable cause) {
        throw new BizException(message,cause);
    }
}

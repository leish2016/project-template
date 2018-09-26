package com.tfb.project.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Pattern;
import lombok.Data;

/**
 * @author leish
 * @date 2017/11/8 18:59
 */
@Data
public class UserReq {


    @ApiModelProperty(value = "用户名", example = "leish")
    @Pattern(regexp = "\\w{2,12}", message = "用户名不正确.")
    private String name;


    @ApiModelProperty(value = "手机号码", example = "15566668888")
    @Pattern(regexp = "0?(13|14|15|18|17)[0-9]{9}", message = "手机号码不正确.")
    private String phone;
}

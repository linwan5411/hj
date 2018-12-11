package cn.jeefast.rest.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class EnrollVo extends LoginParamVo {

    @ApiModelProperty(value="验证码", hidden=false,  required=true, dataType="String")
    @NotNull(message = "验证码不能为空")
    @Length(min = 6,max = 6,message = "验证码长度为6")
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

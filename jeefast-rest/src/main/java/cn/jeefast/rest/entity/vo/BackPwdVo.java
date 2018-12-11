package cn.jeefast.rest.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class BackPwdVo extends MobileVo {

    @ApiModelProperty(value="新密码", hidden=false,  required=true, dataType="String")
    @NotNull(message = "新密码不能为空")
    @Length(min = 6,max = 6,message = "新密码最小长度为6")
    private String newPass;


    @ApiModelProperty(value="确认密码", hidden=false,  required=true, dataType="String")
    @NotNull(message = "确认密码不能为空")
    @Length(min = 6,message = "确认密码最小长度为6")
    private String confirmPass;

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

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

    public String getConfirmPass() {
        return confirmPass;
    }

    public void setConfirmPass(String confirmPass) {
        this.confirmPass = confirmPass;
    }
}

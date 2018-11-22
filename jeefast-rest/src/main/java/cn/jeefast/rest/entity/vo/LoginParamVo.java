package cn.jeefast.rest.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
public class LoginParamVo extends MobileVo {

    @ApiModelProperty(value="密码不能为空", hidden=false,  required=true, dataType="String")
    @NotNull(message = "密码不能为空")
    @Length(min = 6,message = "密码最小长度为6位")
    private String pass;

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}

package cn.jeefast.rest.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
public class MobileVo implements Serializable {

    private static final long serialVersionUID = 7007573663247968726L;

    @ApiModelProperty(value="手机号不能为空", hidden=false,  required=true, dataType="String")
    @NotNull(message = "手机号不能为空")
    @Pattern(regexp = "^(\\d{6,11})", message = "用户手机号码格式不正确",flags = Pattern.Flag.CASE_INSENSITIVE)
    private String mobile;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}

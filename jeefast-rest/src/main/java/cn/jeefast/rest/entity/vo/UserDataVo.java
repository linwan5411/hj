package cn.jeefast.rest.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class UserDataVo implements Serializable{

    private static final long serialVersionUID = 3416207352907826579L;

    @ApiModelProperty(value="手机号", hidden=false,  required=true, dataType="String")
    private String mobile;

    @ApiModelProperty(value="头像", hidden=false,  required=true, dataType="String")
    private String userPortrait;

    @ApiModelProperty(value="昵称", hidden=false,  required=true, dataType="String")
    private String userName;

    @ApiModelProperty(value="token", hidden=false,  required=true, dataType="String")
    @NotNull(message = "登陆token不能为空")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUserPortrait() {
        return userPortrait;
    }

    public void setUserPortrait(String userPortrait) {
        this.userPortrait = userPortrait;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

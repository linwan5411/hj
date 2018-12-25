package cn.jeefast.rest.entity.vo;

import cn.jeefast.rest.entity.BasePage;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Token 的基类
 */
public class TokenPageVo extends BasePage{

    private static final long serialVersionUID = 5990133955487602247L;

    @ApiModelProperty(value="登陆令牌", hidden=false,  required=true, dataType="String")
    @NotNull(message = "token不能为空")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

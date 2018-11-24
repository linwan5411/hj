package cn.jeefast.rest.entity;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Token 的基类
 */
public class TokenPageVo extends BasePage{

    private static final long serialVersionUID = 5990133955487602247L;

    @ApiModelProperty(value="登陆令牌", hidden=false,  required=true, dataType="Double")
    @NotNull(message = "token不能为空")
    private String token;

    @ApiModelProperty(value="纬度", hidden=false,  required=true, dataType="Double")
    private Double latitude;

    @ApiModelProperty(value="经度", hidden=false,  required=true, dataType="Double")
    private Double longitude;

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

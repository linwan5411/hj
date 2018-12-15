package cn.jeefast.rest.entity.vo;

import cn.jeefast.rest.entity.BasePage;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 经纬度，为空默认为重庆
 */
public class CordPageVo extends BasePage implements Serializable{

    private static final long serialVersionUID = 5990133955487602247L;

    @ApiModelProperty(value="维度", hidden=false,  required=false, dataType="Double")
    private Double lat;

    @ApiModelProperty(value="经度", hidden=false,  required=false, dataType="Double")
    private Double lng;

    @ApiModelProperty(value="选择的最后一级地区ID", hidden=false,  required=false, dataType="Long")
    private Long areaId;

    @ApiModelProperty(value="类别编码", hidden=false,  required=false, dataType="String")
    private String categoryCode;

    @ApiModelProperty(value="用户类型", hidden=false,  required=false, dataType="Integer")
    private Integer userType;

    @ApiModelProperty(value="认证类型", hidden=false,  required=false, dataType="Integer")
    private Integer authType;

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getAuthType() {
        return authType;
    }

    public void setAuthType(Integer authType) {
        this.authType = authType;
    }

    public Double getLat() {
        if(lat == null || lat == 0){
            return 29.562849D;
        }
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        if(lng == null || lng == 0){
            return 106.551643D;
        }
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

}

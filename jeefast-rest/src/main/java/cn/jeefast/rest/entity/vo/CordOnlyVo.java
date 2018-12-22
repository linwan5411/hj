package cn.jeefast.rest.entity.vo;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 经纬度，为空默认为重庆
 */
public class CordOnlyVo implements Serializable{

    private static final long serialVersionUID = 5990133955487602247L;

    @ApiModelProperty(value="维度", hidden=false,  required=true, dataType="Double")
    private Double lat;

    @ApiModelProperty(value="经度", hidden=false,  required=true, dataType="Double")
    private Double lng;

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

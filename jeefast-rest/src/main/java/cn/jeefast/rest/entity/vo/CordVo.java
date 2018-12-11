package cn.jeefast.rest.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 经纬度，为空默认为重庆
 */
public class CordVo implements Serializable{

    private static final long serialVersionUID = 5990133955487602247L;

    @ApiModelProperty(value="默认当前位置的城市", hidden=false,  required=true, dataType="Long")
    @NotNull(message = "位置城市不能为空")
    private Long areaId;

    @ApiModelProperty(value="维度", hidden=false,  required=true, dataType="Double")
    private Double lat;

    @ApiModelProperty(value="经度", hidden=false,  required=true, dataType="Double")
    private Double lng;

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
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

package cn.jeefast.rest.entity.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 地址
 */
public class AddrReqVo implements Serializable{

    private static final long serialVersionUID = 5990133955487602247L;

    @ApiModelProperty(value="地址的上级ID", hidden=false,  required=true, dataType="Long")
    private Long areaId;

    public Long getAreaId() {
        if(areaId == null || areaId <= 0){
            return -1L;
        }
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }
}

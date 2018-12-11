package cn.jeefast.rest.entity.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 服务商详情
 */
public class LandRemakVo implements Serializable {

    @ApiModelProperty(value="介绍文字描述", hidden=false,  required=false, dataType="String")
    private String haciendaInfo;

    @ApiModelProperty(value="图片", hidden=false,  required=false, dataType="String")
    private String haciendaImage;

    public String getHaciendaInfo() {
        return haciendaInfo;
    }

    public void setHaciendaInfo(String haciendaInfo) {
        this.haciendaInfo = haciendaInfo;
    }

    public String getHaciendaImage() {
        return haciendaImage;
    }

    public void setHaciendaImage(String haciendaImage) {
        this.haciendaImage = haciendaImage;
    }
}

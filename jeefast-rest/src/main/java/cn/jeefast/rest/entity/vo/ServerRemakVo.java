package cn.jeefast.rest.entity.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 服务商详情
 */
public class ServerRemakVo implements Serializable {

    @ApiModelProperty(value="介绍文字描述", hidden=false,  required=false, dataType="String")
    private String serverInfo;

    @ApiModelProperty(value="图片", hidden=false,  required=false, dataType="String")
    private String serverImage;

    public String getServerInfo() {
        return serverInfo;
    }

    public void setServerInfo(String serverInfo) {
        this.serverInfo = serverInfo;
    }

    public String getServerImage() {
        return serverImage;
    }

    public void setServerImage(String serverImage) {
        this.serverImage = serverImage;
    }
}

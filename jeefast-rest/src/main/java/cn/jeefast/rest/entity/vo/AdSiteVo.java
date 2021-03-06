package cn.jeefast.rest.entity.vo;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 广告位置
 */
public class AdSiteVo implements Serializable{

    private static final long serialVersionUID = 5990133955487602247L;

    @ApiModelProperty(value="类型不能为空", hidden=false,  required=true, dataType="String")
    @NotNull(message = "类型不能为空")
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

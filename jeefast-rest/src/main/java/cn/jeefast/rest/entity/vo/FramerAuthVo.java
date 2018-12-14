package cn.jeefast.rest.entity.vo;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 添加土地的操作
 */
public class FramerAuthVo extends TokenVo {

    private static final long serialVersionUID = -5087852319042682538L;

    @ApiModelProperty(value="认证ID", hidden=false,  required=true, dataType="String")
    private Long farmersId;

    @ApiModelProperty(value="联系电话", hidden=false,  required=true, dataType="String")
    @NotNull(message = "联系电话不能为空")
    private String linkPhone;

    @ApiModelProperty(value="联系人", hidden=false,  required=true, dataType="String")
    @NotNull(message = "联系人不能为空")
    private String linkName;

    @ApiModelProperty(value="农场主名称", hidden=false,  required=false, dataType="String")
    private String farmersName;

    @ApiModelProperty(value="图片", hidden=false,  required=false, dataType="String")
    private String farmersImage;

    @ApiModelProperty(value="简介", hidden=false,  required=true, dataType="String")
    private String farmersRemark;

    @ApiModelProperty(value="类型；1：个人，2企业", hidden=false,  required=true, dataType="String")
    private Integer farmersType;

    public Long getFarmersId() {
        return farmersId;
    }

    public void setFarmersId(Long farmersId) {
        this.farmersId = farmersId;
    }

    public String getLinkPhone() {
        return linkPhone;
    }

    public void setLinkPhone(String linkPhone) {
        this.linkPhone = linkPhone;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public String getFarmersName() {
        return farmersName;
    }

    public void setFarmersName(String farmersName) {
        this.farmersName = farmersName;
    }

    public String getFarmersImage() {
        return farmersImage;
    }

    public void setFarmersImage(String farmersImage) {
        this.farmersImage = farmersImage;
    }

    public String getFarmersRemark() {
        return farmersRemark;
    }

    public void setFarmersRemark(String farmersRemark) {
        this.farmersRemark = farmersRemark;
    }

    public Integer getFarmersType() {
        return farmersType;
    }

    public void setFarmersType(Integer farmersType) {
        this.farmersType = farmersType;
    }
}

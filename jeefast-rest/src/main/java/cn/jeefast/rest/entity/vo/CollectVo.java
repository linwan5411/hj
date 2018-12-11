package cn.jeefast.rest.entity.vo;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * 发表话题
 */
public class CollectVo extends TokenVo {
    private static final long serialVersionUID = 3858985501607274940L;

    @ApiModelProperty(value="收藏的ID", hidden=false,  required=true, dataType="String")
    @NotNull(message = "收藏的ID")
    private Long objectId;

    @ApiModelProperty(value="收藏类型", hidden=false,  required=true, dataType="String")
    private Integer collectType;

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public Integer getCollectType() {
        return collectType;
    }

    public void setCollectType(Integer collectType) {
        this.collectType = collectType;
    }
}

package cn.jeefast.rest.entity.vo;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * 发表话题
 */
public class OrderVo extends TokenVo {
    private static final long serialVersionUID = 3858985501607274940L;

    @ApiModelProperty(value="收藏的ID", hidden=false,  required=true, dataType="String")
    @NotNull(message = "被联系的ID")
    private Long objectId;

    @ApiModelProperty(value="被联系的类型1,2", hidden=false,  required=true, dataType="String")
    @NotNull(message = "被联系的类型1,2")
    private Integer userType;

    @ApiModelProperty(value="联系人电话", hidden=false,  required=true, dataType="String")
    @NotNull(message = "联系人电话不能为空")
    private String userMobile;

    @ApiModelProperty(value="联系人真实姓名", hidden=false,  required=true, dataType="String")
    @NotNull(message = "联系人真实姓名不能为空")
    private String userName;

    @ApiModelProperty(value="验证码", hidden=false,  required=true, dataType="String")
    @NotNull(message = "验证码不能为空")
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

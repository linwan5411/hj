package cn.jeefast.rest.entity.vo;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class MessageVo extends MobileVo {

    @ApiModelProperty(value="类型不能为空", hidden=false,  required=true, dataType="String")
    @NotNull(message = "类型不能为空")
    @Pattern(regexp = "^[1,2,3]", message = "类型只能为1,2",flags = Pattern.Flag.CASE_INSENSITIVE)
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

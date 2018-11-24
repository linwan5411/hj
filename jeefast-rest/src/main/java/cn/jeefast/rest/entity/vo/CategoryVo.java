package cn.jeefast.rest.entity.vo;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 类别编码类型
 */
public class CategoryVo implements Serializable{

    private static final long serialVersionUID = 5990133955487602247L;

    @ApiModelProperty(value="类型不能为空", hidden=false,  required=true, dataType="String")
    @NotNull(message = "类型不能为空")
    @Pattern(regexp = "^['1','9','15']\\w$", message = "类型只能为1,9,15",flags = Pattern.Flag.CASE_INSENSITIVE)
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

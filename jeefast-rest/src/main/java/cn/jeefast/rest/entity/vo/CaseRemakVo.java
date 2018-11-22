package cn.jeefast.rest.entity.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 服务商详情
 */
public class CaseRemakVo implements Serializable {

    @ApiModelProperty(value="介绍文字描述", hidden=false,  required=false, dataType="String")
    private String caseInfo;

    @ApiModelProperty(value="图片", hidden=false,  required=false, dataType="String")
    private String caseImage;

    public String getCaseInfo() {
        return caseInfo;
    }

    public void setCaseInfo(String caseInfo) {
        this.caseInfo = caseInfo;
    }

    public String getCaseImage() {
        return caseImage;
    }

    public void setCaseImage(String caseImage) {
        this.caseImage = caseImage;
    }
}

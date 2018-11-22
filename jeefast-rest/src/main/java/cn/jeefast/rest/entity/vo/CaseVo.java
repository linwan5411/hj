package cn.jeefast.rest.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 服务商案例
 */
public class CaseVo extends TokenVo {

    private static final long serialVersionUID = -5087852319042682538L;

    @ApiModelProperty(value="案例", hidden=false,  required=true, dataType="String")
    private Long caseId;

    @ApiModelProperty(value="项目名称", hidden=false,  required=true, dataType="String")
    @NotNull(message = "项目名称不能为空")
    @Length(max = 120,message = "项目名称称最大长度120")
    private String caseName;

    @ApiModelProperty(value="服务的亩数", hidden=false,  required=true, dataType="String")
    @NotNull(message = "服务的亩数")
    private String caseMax;

    @ApiModelProperty(value="服务时间", hidden=false,  required=true, dataType="String")
    private String caseTime;

    @ApiModelProperty(value="文字介绍", hidden=false,  required=true, dataType="JSON")
    private List<CaseRemakVo> list;

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public String getCaseMax() {
        return caseMax;
    }

    public void setCaseMax(String caseMax) {
        this.caseMax = caseMax;
    }

    public String getCaseTime() {
        return caseTime;
    }

    public void setCaseTime(String caseTime) {
        this.caseTime = caseTime;
    }

    public List<CaseRemakVo> getList() {
        return list;
    }

    public void setList(List<CaseRemakVo> list) {
        this.list = list;
    }
}

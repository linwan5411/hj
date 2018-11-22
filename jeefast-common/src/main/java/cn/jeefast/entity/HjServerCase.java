package cn.jeefast.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import cn.jeefast.entity.base.BaseEntity;

import java.util.List;

/**
 * <p>
 * 服务商案例
 * </p>
 *
 * @author zhihang
 * @since 2018-11-20
 */
@TableName("hj_server_case")
public class HjServerCase extends BaseEntity {

    private static final long serialVersionUID = 1L;

	@TableField("case_id")
	private Long caseId;
	@TableField("server_id")
	private Long serverId;
    /**
     * 服务类型
     */
	@TableField("server_type")
	private String serverType;
    /**
     * 项目名称
     */
	@TableField("case_name")
	private String caseName;
    /**
     * 服务的亩数
     */
	@TableField("case_max")
	private String caseMax;
    /**
     * 服务时间
     */
	@TableField("case_time")
	private String caseTime;

	@TableField("case_image")
	private String caseImage;

	public String getCaseImage() {
		return caseImage;
	}

	public void setCaseImage(String caseImage) {
		this.caseImage = caseImage;
	}

	public Long getCaseId() {
		return caseId;
	}

	public void setCaseId(Long caseId) {
		this.caseId = caseId;
	}

	public Long getServerId() {
		return serverId;
	}

	public void setServerId(Long serverId) {
		this.serverId = serverId;
	}

	public String getServerType() {
		return serverType;
	}

	public void setServerType(String serverType) {
		this.serverType = serverType;
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

	@Override
	public String toString() {
		return "HjServerCase{" +
			", caseId=" + caseId +
			", serverId=" + serverId +
			", serverType=" + serverType +
			", caseName=" + caseName +
			", caseMax=" + caseMax +
			", caseTime=" + caseTime +
			"}";
	}
}

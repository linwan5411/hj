package cn.jeefast.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import cn.jeefast.entity.base.BaseEntity;

/**
 * <p>
 * 服务商案例详情介绍
 * </p>
 *
 * @author zhihang
 * @since 2018-11-20
 */
@TableName("hj_server_case_remark")
public class HjServerCaseRemark extends BaseEntity {

    private static final long serialVersionUID = 1L;

	@TableField("case_id")
	private Long caseId;
    /**
     * 介绍文字描述
     */
	@TableField("case_info")
	private String caseInfo;
    /**
     * 1:显示
     */
	@TableField("case_show")
	private Integer caseShow;
    /**
     * 图片
     */
	@TableField("case_image")
	private String caseImage;


	public Long getCaseId() {
		return caseId;
	}

	public void setCaseId(Long caseId) {
		this.caseId = caseId;
	}

	public String getCaseInfo() {
		return caseInfo;
	}

	public void setCaseInfo(String caseInfo) {
		this.caseInfo = caseInfo;
	}

	public Integer getCaseShow() {
		return caseShow;
	}

	public void setCaseShow(Integer caseShow) {
		this.caseShow = caseShow;
	}

	public String getCaseImage() {
		return caseImage;
	}

	public void setCaseImage(String caseImage) {
		this.caseImage = caseImage;
	}

	@Override
	public String toString() {
		return "HjServerCaseRemark{" +
			", caseId=" + caseId +
			", caseInfo=" + caseInfo +
			", caseShow=" + caseShow +
			", caseImage=" + caseImage +
			"}";
	}
}

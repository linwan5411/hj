package cn.jeefast.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import cn.jeefast.entity.base.BaseEntity;

/**
 * <p>
 * 服务类别编码
 * </p>
 *
 * @author zhihang
 * @since 2018-11-18
 */
@TableName("hj_server_code")
public class HjServerCode extends BaseEntity {

    private static final long serialVersionUID = 1L;

	@TableField("server_category")
	private String serverCategory;
	@TableField("server_remark")
	private String serverRemark;
    /**
     * 图片地址
     */
	@TableField("server_icon")
	private String serverIcon;
	@TableField("parent_id")
	private Long parentId;
    /**
     * 排序
     */
	@TableField("server_sort")
	private Integer serverSort;
    /**
     * 编码
     */
	@TableField("category_code")
	private String categoryCode;
    /**
     * 0:停用，1：启用
     */
	@TableField("data_status")
	private Integer dataStatus;


	public String getServerCategory() {
		return serverCategory;
	}

	public void setServerCategory(String serverCategory) {
		this.serverCategory = serverCategory;
	}

	public String getServerRemark() {
		return serverRemark;
	}

	public void setServerRemark(String serverRemark) {
		this.serverRemark = serverRemark;
	}

	public String getServerIcon() {
		return serverIcon;
	}

	public void setServerIcon(String serverIcon) {
		this.serverIcon = serverIcon;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Integer getServerSort() {
		return serverSort;
	}

	public void setServerSort(Integer serverSort) {
		this.serverSort = serverSort;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public Integer getDataStatus() {
		return dataStatus;
	}

	public void setDataStatus(Integer dataStatus) {
		this.dataStatus = dataStatus;
	}

	@Override
	public String toString() {
		return "HjServerCode{" +
			", serverCategory=" + serverCategory +
			", serverRemark=" + serverRemark +
			", serverIcon=" + serverIcon +
			", parentId=" + parentId +
			", serverSort=" + serverSort +
			", categoryCode=" + categoryCode +
			", dataStatus=" + dataStatus +
			"}";
	}
}

package cn.jeefast.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import cn.jeefast.entity.base.BaseEntity;

/**
 * <p>
 * 农场主认证
 * </p>
 *
 * @author zhihang
 * @since 2018-12-13
 */
@TableName("hj_farmers_info")
public class HjFarmersInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 农场主认证的ID
     */
	@TableField("farmers_id")
	private Long farmersId;
    /**
     * 用户ID
     */
	@TableField("user_id")
	private Long userId;
    /**
     * 农场主名称
     */
	@TableField("farmers_name")
	private String farmersName;
    /**
     * 联系电话
     */
	@TableField("link_phone")
	private String linkPhone;
    /**
     * 1：个人，2:企业
     */
	@TableField("farmers_type")
	private Integer farmersType;
	@TableField("link_name")
	private String linkName;
    /**
     * 0：待审核，1：审核通过，-1：审核未通过
     */
	@TableField("auth_status")
	private Integer authStatus;
    /**
     * 图片
     */
	@TableField("farmers_image")
	private String farmersImage;
    /**
     * 介绍
     */
	@TableField("farmers_remark")
	private String farmersRemark;

	/**
     * 经度
     */
	@TableField("longitude")
	private double longitude;

	/**
	 * 纬度
	 */
	@TableField("latitude")
	private double latitude;

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public Long getFarmersId() {
		return farmersId;
	}

	public void setFarmersId(Long farmersId) {
		this.farmersId = farmersId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFarmersName() {
		return farmersName;
	}

	public void setFarmersName(String farmersName) {
		this.farmersName = farmersName;
	}

	public String getLinkPhone() {
		return linkPhone;
	}

	public void setLinkPhone(String linkPhone) {
		this.linkPhone = linkPhone;
	}

	public Integer getFarmersType() {
		return farmersType;
	}

	public void setFarmersType(Integer farmersType) {
		this.farmersType = farmersType;
	}

	public String getLinkName() {
		return linkName;
	}

	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	public Integer getAuthStatus() {
		return authStatus;
	}

	public void setAuthStatus(Integer authStatus) {
		this.authStatus = authStatus;
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

	@Override
	public String toString() {
		return "HjFarmersInfo{" +
			", farmersId=" + farmersId +
			", userId=" + userId +
			", farmersName=" + farmersName +
			", linkPhone=" + linkPhone +
			", farmersType=" + farmersType +
			", linkName=" + linkName +
			", authStatus=" + authStatus +
			", farmersImage=" + farmersImage +
			", farmersRemark=" + farmersRemark +
			"}";
	}
}

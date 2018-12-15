package cn.jeefast.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import cn.jeefast.entity.base.BaseEntity;

import java.util.List;

/**
 * <p>
 * 农场主
 * </p>
 *
 * @author zhihang
 * @since 2018-11-22
 */
@TableName("hj_hacienda_info")
public class HjHaciendaInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

	/**
	 * 农场主ID
	 */
	@TableField("farmers_id")
    private Long farmersId;

    /**
     * 土地Id
     */
	@TableField("hacienda_id")
	private Long haciendaId;
    /**
     * 会员ID
     */
	@TableField("user_id")
	private Long userId;
    /**
     * 介绍
     */
	@TableField("hacienda_remark")
	private String haciendaRemark;
    /**
     * 农场名称
     */
	@TableField("hacienda_name")
	private String haciendaName;
    /**
     * 联系电话
     */
	@TableField("link_phone")
	private String linkPhone;
    /**
     * 联系人
     */
	@TableField("link_name")
	private String linkName;
    /**
     * 2:企业，1：个人
     */
	@TableField("hacienda_type")
	private Integer haciendaType;
    /**
     * 邮箱
     */
	@TableField("hacienda_email")
	private String haciendaEmail;
    /**
     * 注册工商图片
     */
	@TableField("hacienda_reg_image")
	private String haciendaRegImage;
    /**
     * 附着种植范围
     */
	@TableField("hacienda_scope")
	private String haciendaScope;
    /**
     * 图片地址，采用,进行分割
     */
	@TableField("hacienda_image")
	private String haciendaImage;
    /**
     * 土地性质
     */
	@TableField("hacienda_land")
	private String haciendaLand;
    /**
     * 土地性质类别 
     */
	@TableField("server_category")
	private String serverCategory;

	/**
	 * 所需服务的编码
	 */
	@TableField("need_server")
	private String needServer;

	/**
	 * 所需服务的类别名称
	 */
	@TableField("need_server_name")
	private String needServerName;
    /**
     * 土地面积
     */
	@TableField("server_max")
	private Double serverMax;
    /**
     * 托管可使用面积
     */
	@TableField("server_use_max")
	private Double serverUseMax;
    /**
     * 详细地址
     */
	@TableField("detail_addr")
	private String detailAddr;
    /**
     * 省
     */
	private Long provice;
    /**
     * 市
     */
	private Long city;
    /**
     * 区
     */
	private Long district;
    /**
     * 最小地址编码
     */
	@TableField("area_code")
	private String areaCode;
    /**
     * 纬度
     */
	private Double latitude;
    /**
     * 经度
     */
	private Double longitude;
    /**
     * 1:展示，0：不展示
     */
	@TableField("hacienda_show")
	private Integer haciendaShow;
    /**
     * 排序
     */
	@TableField("hacienda_sort")
	private Integer haciendaSort;
    /**
     * 0:待审核，1：审核中，2：审核通过
     */
	@TableField("auth_status")
	private Integer authStatus;

	/**
	 * 经济范围
	 */
	@TableField(exist = false)
	private List<String> categoryList;

	/**
	 * 土地图片
	 */
	@TableField(exist = false)
	private List<HjHaciendaRemark> remarks;

	/**
	 * 农场主的头像
	 */
	private String headerImage;

	/**
	 * 农场主的昵称
	 */
	private String framersNickName;

	public Long getFarmersId() {
		return farmersId;
	}

	public void setFarmersId(Long farmersId) {
		this.farmersId = farmersId;
	}

	public String getHeaderImage() {
		return headerImage;
	}

	public void setHeaderImage(String headerImage) {
		this.headerImage = headerImage;
	}

	public String getFramersNickName() {
		return framersNickName;
	}

	public void setFramersNickName(String framersNickName) {
		this.framersNickName = framersNickName;
	}

	public String getNeedServer() {
		return needServer;
	}

	public void setNeedServer(String needServer) {
		this.needServer = needServer;
	}

	public String getNeedServerName() {
		return needServerName;
	}

	public void setNeedServerName(String needServerName) {
		this.needServerName = needServerName;
	}

	public List<HjHaciendaRemark> getRemarks() {
		return remarks;
	}

	public void setRemarks(List<HjHaciendaRemark> remarks) {
		this.remarks = remarks;
	}

	public List<String> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<String> categoryList) {
		this.categoryList = categoryList;
	}

	public Long getHaciendaId() {
		return haciendaId;
	}

	public void setHaciendaId(Long haciendaId) {
		this.haciendaId = haciendaId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getHaciendaRemark() {
		return haciendaRemark;
	}

	public void setHaciendaRemark(String haciendaRemark) {
		this.haciendaRemark = haciendaRemark;
	}

	public String getHaciendaName() {
		return haciendaName;
	}

	public void setHaciendaName(String haciendaName) {
		this.haciendaName = haciendaName;
	}

	public String getLinkPhone() {
		return linkPhone;
	}

	public void setLinkPhone(String linkPhone) {
		this.linkPhone = linkPhone;
	}

	public String getLinkName() {
		return linkName;
	}

	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	public Integer getHaciendaType() {
		return haciendaType;
	}

	public void setHaciendaType(Integer haciendaType) {
		this.haciendaType = haciendaType;
	}

	public String getHaciendaEmail() {
		return haciendaEmail;
	}

	public void setHaciendaEmail(String haciendaEmail) {
		this.haciendaEmail = haciendaEmail;
	}

	public String getHaciendaRegImage() {
		return haciendaRegImage;
	}

	public void setHaciendaRegImage(String haciendaRegImage) {
		this.haciendaRegImage = haciendaRegImage;
	}

	public String getHaciendaScope() {
		return haciendaScope;
	}

	public void setHaciendaScope(String haciendaScope) {
		this.haciendaScope = haciendaScope;
	}

	public String getHaciendaImage() {
		return haciendaImage;
	}

	public void setHaciendaImage(String haciendaImage) {
		this.haciendaImage = haciendaImage;
	}

	public String getHaciendaLand() {
		return haciendaLand;
	}

	public void setHaciendaLand(String haciendaLand) {
		this.haciendaLand = haciendaLand;
	}

	public String getServerCategory() {
		return serverCategory;
	}

	public void setServerCategory(String serverCategory) {
		this.serverCategory = serverCategory;
	}

	public Double getServerMax() {
		return serverMax;
	}

	public void setServerMax(Double serverMax) {
		this.serverMax = serverMax;
	}

	public Double getServerUseMax() {
		return serverUseMax;
	}

	public void setServerUseMax(Double serverUseMax) {
		this.serverUseMax = serverUseMax;
	}

	public String getDetailAddr() {
		return detailAddr;
	}

	public void setDetailAddr(String detailAddr) {
		this.detailAddr = detailAddr;
	}

	public Long getProvice() {
		return provice;
	}

	public void setProvice(Long provice) {
		this.provice = provice;
	}

	public Long getCity() {
		return city;
	}

	public void setCity(Long city) {
		this.city = city;
	}

	public Long getDistrict() {
		return district;
	}

	public void setDistrict(Long district) {
		this.district = district;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Integer getHaciendaShow() {
		return haciendaShow;
	}

	public void setHaciendaShow(Integer haciendaShow) {
		this.haciendaShow = haciendaShow;
	}

	public Integer getHaciendaSort() {
		return haciendaSort;
	}

	public void setHaciendaSort(Integer haciendaSort) {
		this.haciendaSort = haciendaSort;
	}

	public Integer getAuthStatus() {
		return authStatus;
	}

	public void setAuthStatus(Integer authStatus) {
		this.authStatus = authStatus;
	}

	@Override
	public String toString() {
		return "HjHaciendaInfo{" +
			", haciendaId=" + haciendaId +
			", userId=" + userId +
			", haciendaRemark=" + haciendaRemark +
			", haciendaName=" + haciendaName +
			", linkPhone=" + linkPhone +
			", linkName=" + linkName +
			", haciendaType=" + haciendaType +
			", haciendaEmail=" + haciendaEmail +
			", haciendaRegImage=" + haciendaRegImage +
			", haciendaScope=" + haciendaScope +
			", haciendaImage=" + haciendaImage +
			", haciendaLand=" + haciendaLand +
			", serverCategory=" + serverCategory +
			", serverMax=" + serverMax +
			", serverUseMax=" + serverUseMax +
			", detailAddr=" + detailAddr +
			", provice=" + provice +
			", city=" + city +
			", district=" + district +
			", areaCode=" + areaCode +
			", latitude=" + latitude +
			", longitude=" + longitude +
			", haciendaShow=" + haciendaShow +
			", haciendaSort=" + haciendaSort +
			", authStatus=" + authStatus +
			"}";
	}
}

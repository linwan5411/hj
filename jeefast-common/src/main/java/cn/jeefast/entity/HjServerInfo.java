package cn.jeefast.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import cn.jeefast.entity.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * <p>
 * 服务商详请
 * </p>
 *
 * @author zhihang
 * @since 2018-11-20
 */
@TableName("hj_server_info")
public class HjServerInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 服务商ID
     */
	@TableField("server_id")
	private Long serverId;
    /**
     * 会员ID
     */
	@JsonIgnore
	@TableField("user_id")
	private Long userId;
    /**
     * 企业名称
     */
	@TableField("company_name")
	private String companyName;
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
     * 邮箱
     */
	@JsonIgnore
	@TableField("company_email")
	private String companyEmail;
    /**
     * 经营范围
     */
	@TableField("company_scope")
	private String companyScope;
    /**
     * 图片主图
     */
	@TableField("company_image")
	private String companyImage;
    /**
     * 网站介绍
     */
	@JsonIgnore
	@TableField("company_website")
	private String companyWebsite;
    /**
     * 服务的领域
     */
	@TableField("server_category")
	private String serverCategory;
    /**
     * 注册时间
     */
	@JsonIgnore
	@TableField("server_reg_time")
	private String serverRegTime;
    /**
     * 营业执照图片
     */
	@TableField("server_reg_image")
	private String serverRegImage;
    /**
     * 工商注册码
     */
	@JsonIgnore
	@TableField("server_registration")
	private String serverRegistration;
    /**
     * 1:个人，2：企业
     */
	@TableField("server_type")
	private Integer serverType;
    /**
     * 服务的亩数，单位亩
     */
	@TableField("server_max")
	private Double serverMax;
    /**
     * 服务领域编码
     */
	@JsonIgnore
	@TableField("server_codes")
	private String serverCodes;
    /**
     * 注册资本
     */
	@TableField("regist_capital")
	private String registCapital;
    /**
     * 详细地址
     */
	@TableField("detail_addr")
	private String detailAddr;
    /**
     * 案例数量
     */
	@TableField("case_num")
	private Integer caseNum;
    /**
     * 客户数量
     */
	@JsonIgnore
	@TableField("client_num")
	private Integer clientNum;
    /**
     * 省
     */
	@JsonIgnore
	private Long provice;
    /**
     * 市
     */
	@JsonIgnore
	private Long city;
    /**
     * 区
     */
	@JsonIgnore
	private Long district;
    /**
     * 最小地址编码
     */
	@JsonIgnore
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
    @JsonIgnore
	@TableField("server_show")
	private Integer serverShow;
    /**
     * 排序
     */
	@JsonIgnore
	@TableField("server_sort")
	private Integer serverSort;
    /**
     * 0:待审核，1：审核中，2：审核通过
     */
	@JsonIgnore
	@TableField("auth_status")
	private Integer authStatus;

	/**
	 * 服务的领域
	 */
	private List<String> categoryList;

	/**
	 * 简介
	 */
	private List<HjServerRemak> remaks;


	/**
	 * 案例
	 */
	private List<HjServerCase> caseList;

	public List<String> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<String> categoryList) {
		this.categoryList = categoryList;
	}

	public List<HjServerRemak> getRemaks() {
		return remaks;
	}

	public void setRemaks(List<HjServerRemak> remaks) {
		this.remaks = remaks;
	}

	public List<HjServerCase> getCaseList() {
		return caseList;
	}

	public void setCaseList(List<HjServerCase> caseList) {
		this.caseList = caseList;
	}

	public Long getServerId() {
		return serverId;
	}

	public void setServerId(Long serverId) {
		this.serverId = serverId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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

	public String getCompanyEmail() {
		return companyEmail;
	}

	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}

	public String getCompanyScope() {
		return companyScope;
	}

	public void setCompanyScope(String companyScope) {
		this.companyScope = companyScope;
	}

	public String getCompanyImage() {
		return companyImage;
	}

	public void setCompanyImage(String companyImage) {
		this.companyImage = companyImage;
	}

	public String getCompanyWebsite() {
		return companyWebsite;
	}

	public void setCompanyWebsite(String companyWebsite) {
		this.companyWebsite = companyWebsite;
	}

	public String getServerCategory() {
		return serverCategory;
	}

	public void setServerCategory(String serverCategory) {
		this.serverCategory = serverCategory;
	}

	public String getServerRegTime() {
		return serverRegTime;
	}

	public void setServerRegTime(String serverRegTime) {
		this.serverRegTime = serverRegTime;
	}

	public String getServerRegImage() {
		return serverRegImage;
	}

	public void setServerRegImage(String serverRegImage) {
		this.serverRegImage = serverRegImage;
	}

	public String getServerRegistration() {
		return serverRegistration;
	}

	public void setServerRegistration(String serverRegistration) {
		this.serverRegistration = serverRegistration;
	}

	public Integer getServerType() {
		return serverType;
	}

	public void setServerType(Integer serverType) {
		this.serverType = serverType;
	}

	public Double getServerMax() {
		return serverMax;
	}

	public void setServerMax(Double serverMax) {
		this.serverMax = serverMax;
	}

	public String getServerCodes() {
		return serverCodes;
	}

	public void setServerCodes(String serverCodes) {
		this.serverCodes = serverCodes;
	}

	public String getRegistCapital() {
		return registCapital;
	}

	public void setRegistCapital(String registCapital) {
		this.registCapital = registCapital;
	}

	public String getDetailAddr() {
		return detailAddr;
	}

	public void setDetailAddr(String detailAddr) {
		this.detailAddr = detailAddr;
	}

	public Integer getCaseNum() {
		return caseNum;
	}

	public void setCaseNum(Integer caseNum) {
		this.caseNum = caseNum;
	}

	public Integer getClientNum() {
		return clientNum;
	}

	public void setClientNum(Integer clientNum) {
		this.clientNum = clientNum;
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

	public Integer getServerShow() {
		return serverShow;
	}

	public void setServerShow(Integer serverShow) {
		this.serverShow = serverShow;
	}

	public Integer getServerSort() {
		return serverSort;
	}

	public void setServerSort(Integer serverSort) {
		this.serverSort = serverSort;
	}

	public Integer getAuthStatus() {
		return authStatus;
	}

	public void setAuthStatus(Integer authStatus) {
		this.authStatus = authStatus;
	}

	@Override
	public String toString() {
		return "HjServerInfo{" +
			", serverId=" + serverId +
			", userId=" + userId +
			", companyName=" + companyName +
			", linkPhone=" + linkPhone +
			", linkName=" + linkName +
			", companyEmail=" + companyEmail +
			", companyScope=" + companyScope +
			", companyImage=" + companyImage +
			", companyWebsite=" + companyWebsite +
			", serverCategory=" + serverCategory +
			", serverRegTime=" + serverRegTime +
			", serverRegImage=" + serverRegImage +
			", serverRegistration=" + serverRegistration +
			", serverType=" + serverType +
			", serverMax=" + serverMax +
			", serverCodes=" + serverCodes +
			", registCapital=" + registCapital +
			", detailAddr=" + detailAddr +
			", caseNum=" + caseNum +
			", clientNum=" + clientNum +
			", provice=" + provice +
			", city=" + city +
			", district=" + district +
			", areaCode=" + areaCode +
			", latitude=" + latitude +
			", longitude=" + longitude +
			", serverShow=" + serverShow +
			", serverSort=" + serverSort +
			", authStatus=" + authStatus +
			"}";
	}
}

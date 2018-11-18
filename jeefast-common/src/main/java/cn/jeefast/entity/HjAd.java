package cn.jeefast.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import cn.jeefast.entity.base.BaseEntity;

/**
 * <p>
 * 广告
 * </p>
 *
 * @author zhihang
 * @since 2018-11-18
 */
@TableName("hj_ad")
public class HjAd extends BaseEntity {

    private static final long serialVersionUID = 1L;

	@TableField("ad_site_id")
	private Long adSiteId;
    /**
     * 广告ID
     */
	@TableField("ad_id")
	private Long adId;
    /**
     * 名称
     */
	@TableField("ad_name")
	private String adName;
    /**
     * 排序
     */
	@TableField("ad_sort")
	private Integer adSort;
    /**
     * 1：显示，0：不显示
     */
	@TableField("ad_status")
	private Integer adStatus;
    /**
     * 跳转地址
     */
	@TableField("ad_url")
	private String adUrl;
    /**
     * 点击次数
     */
	@TableField("ad_time")
	private Integer adTime;
    /**
     * 尺寸
     */
	@TableField("ad_image")
	private String adImage;


	public Long getAdSiteId() {
		return adSiteId;
	}

	public void setAdSiteId(Long adSiteId) {
		this.adSiteId = adSiteId;
	}

	public Long getAdId() {
		return adId;
	}

	public void setAdId(Long adId) {
		this.adId = adId;
	}

	public String getAdName() {
		return adName;
	}

	public void setAdName(String adName) {
		this.adName = adName;
	}

	public Integer getAdSort() {
		return adSort;
	}

	public void setAdSort(Integer adSort) {
		this.adSort = adSort;
	}

	public Integer getAdStatus() {
		return adStatus;
	}

	public void setAdStatus(Integer adStatus) {
		this.adStatus = adStatus;
	}

	public String getAdUrl() {
		return adUrl;
	}

	public void setAdUrl(String adUrl) {
		this.adUrl = adUrl;
	}

	public Integer getAdTime() {
		return adTime;
	}

	public void setAdTime(Integer adTime) {
		this.adTime = adTime;
	}

	public String getAdImage() {
		return adImage;
	}

	public void setAdImage(String adImage) {
		this.adImage = adImage;
	}

	@Override
	public String toString() {
		return "HjAd{" +
			", adSiteId=" + adSiteId +
			", adId=" + adId +
			", adName=" + adName +
			", adSort=" + adSort +
			", adStatus=" + adStatus +
			", adUrl=" + adUrl +
			", adTime=" + adTime +
			", adImage=" + adImage +
			"}";
	}
}

package cn.jeefast.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import cn.jeefast.entity.base.BaseEntity;

/**
 * <p>
 * 广告位置
 * </p>
 *
 * @author zhihang
 * @since 2018-11-18
 */
@TableName("hj_ad_site")
public class HjAdSite extends BaseEntity {

    private static final long serialVersionUID = 1L;

	@TableField("ad_site_id")
	private Long adSiteId;
    /**
     * 广告位置
     */
	@TableField("ad_site_name")
	private String adSiteName;
    /**
     * 1:图片，2：文字
     */
	@TableField("ad_type")
	private Integer adType;
    /**
     * 尺寸宽度
     */
	@TableField("ad_size_w")
	private Integer adSizeW;
    /**
     * 尺寸高
     */
	@TableField("ad_size_h")
	private Integer adSizeH;


	public Long getAdSiteId() {
		return adSiteId;
	}

	public void setAdSiteId(Long adSiteId) {
		this.adSiteId = adSiteId;
	}

	public String getAdSiteName() {
		return adSiteName;
	}

	public void setAdSiteName(String adSiteName) {
		this.adSiteName = adSiteName;
	}

	public Integer getAdType() {
		return adType;
	}

	public void setAdType(Integer adType) {
		this.adType = adType;
	}

	public Integer getAdSizeW() {
		return adSizeW;
	}

	public void setAdSizeW(Integer adSizeW) {
		this.adSizeW = adSizeW;
	}

	public Integer getAdSizeH() {
		return adSizeH;
	}

	public void setAdSizeH(Integer adSizeH) {
		this.adSizeH = adSizeH;
	}

	@Override
	public String toString() {
		return "HjAdSite{" +
			", adSiteId=" + adSiteId +
			", adSiteName=" + adSiteName +
			", adType=" + adType +
			", adSizeW=" + adSizeW +
			", adSizeH=" + adSizeH +
			"}";
	}
}

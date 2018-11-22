package cn.jeefast.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import cn.jeefast.entity.base.BaseEntity;

/**
 * <p>
 * 农场的土地介绍相关
 * </p>
 *
 * @author zhihang
 * @since 2018-11-22
 */
@TableName("hj_hacienda_remark")
public class HjHaciendaRemark extends BaseEntity {

    private static final long serialVersionUID = 1L;

	@TableField("hacienda_id")
	private Long haciendaId;
    /**
     * 介绍图片
     */
	@TableField("hacienda_image")
	private String haciendaImage;
    /**
     * 介绍内容
     */
	@TableField("hacienda_info")
	private String haciendaInfo;


	public Long getHaciendaId() {
		return haciendaId;
	}

	public void setHaciendaId(Long haciendaId) {
		this.haciendaId = haciendaId;
	}

	public String getHaciendaImage() {
		return haciendaImage;
	}

	public void setHaciendaImage(String haciendaImage) {
		this.haciendaImage = haciendaImage;
	}

	public String getHaciendaInfo() {
		return haciendaInfo;
	}

	public void setHaciendaInfo(String haciendaInfo) {
		this.haciendaInfo = haciendaInfo;
	}

	@Override
	public String toString() {
		return "HjHaciendaRemark{" +
			", haciendaId=" + haciendaId +
			", haciendaImage=" + haciendaImage +
			", haciendaInfo=" + haciendaInfo +
			"}";
	}
}

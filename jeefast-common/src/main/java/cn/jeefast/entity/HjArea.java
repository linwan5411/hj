package cn.jeefast.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import cn.jeefast.entity.base.BaseEntity;

/**
 * <p>
 * 地区码表
 * </p>
 *
 * @author zhihang
 * @since 2018-11-17
 */
@TableName("hj_area")
public class HjArea extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 地区Id
     */
	@TableField("area_id")
	private Long areaId;
    /**
     * 地区编码
     */
	@TableField("area_code")
	private String areaCode;
    /**
     * 地区名
     */
	@TableField("area_name")
	private String areaName;
    /**
     * 地区级别（1:省份province,2:市city,3:区县district,4:街道street）
     */
	private Integer level;
    /**
     * 城市编码
     */
	@TableField("city_code")
	private String cityCode;
    /**
     * 关系Code
     */
	@TableField("relation_code")
	private String relationCode;
    /**
     * 城市中心点（即：经纬度坐标）
     */
	private String center;
    /**
     * 地区父节点
     */
	@TableField("parent_id")
	private Integer parentId;


	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getRelationCode() {
		return relationCode;
	}

	public void setRelationCode(String relationCode) {
		this.relationCode = relationCode;
	}

	public String getCenter() {
		return center;
	}

	public void setCenter(String center) {
		this.center = center;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	@Override
	public String toString() {
		return "HjArea{" +
			", areaId=" + areaId +
			", areaCode=" + areaCode +
			", areaName=" + areaName +
			", level=" + level +
			", cityCode=" + cityCode +
			", relationCode=" + relationCode +
			", center=" + center +
			", parentId=" + parentId +
			"}";
	}
}

package cn.jeefast.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import cn.jeefast.entity.base.BaseEntity;

/**
 * <p>
 * 用户的收藏表
 * </p>
 *
 * @author zhihang
 * @since 2018-11-24
 */
@TableName("hj_user_collect")
public class HjUserCollect extends BaseEntity {

    private static final long serialVersionUID = 1L;

	@TableField("user_id")
	private Long userId;
    /**
     * 1:服务商，2：农场，3：文章
     */
	@TableField("collect_type")
	private Integer collectType;
    /**
     * 对于的ID
     */
	@TableField("object_id")
	private Long objectId;
    /**
     * 标题
     */
	@TableField("object_title")
	private String objectTitle;
    /**
     * 图片地址
     */
	@TableField("object_image")
	private String objectImage;

	@TableField("collect_id")
	private Long collectId;

	public Long getCollectId() {
		return collectId;
	}

	public void setCollectId(Long collectId) {
		this.collectId = collectId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getCollectType() {
		return collectType;
	}

	public void setCollectType(Integer collectType) {
		this.collectType = collectType;
	}

	public Long getObjectId() {
		return objectId;
	}

	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}

	public String getObjectTitle() {
		return objectTitle;
	}

	public void setObjectTitle(String objectTitle) {
		this.objectTitle = objectTitle;
	}

	public String getObjectImage() {
		return objectImage;
	}

	public void setObjectImage(String objectImage) {
		this.objectImage = objectImage;
	}

	@Override
	public String toString() {
		return "HjUserCollect{" +
			", userId=" + userId +
			", collectType=" + collectType +
			", objectId=" + objectId +
			", objectTitle=" + objectTitle +
			", objectImage=" + objectImage +
			"}";
	}
}

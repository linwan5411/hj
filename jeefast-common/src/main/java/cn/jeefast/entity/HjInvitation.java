package cn.jeefast.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import cn.jeefast.entity.base.BaseEntity;

/**
 * <p>
 * 帖子
 * </p>
 *
 * @author zhihang
 * @since 2018-11-21
 */
@TableName("hj_invitation")
public class HjInvitation extends BaseEntity {

    private static final long serialVersionUID = 1L;

	@TableField("user_id")
	private Long userId;
	@TableField("invitation_id")
	private Long invitationId;
    /**
     * 图片地址
     */
	@TableField("invitation_images")
	private String invitationImages;
    /**
     * 头像
     */
	@TableField("user_header")
	private String userHeader;
    /**
     * 昵称
     */
	@TableField("user_name")
	private String userName;
    /**
     * 类型名称
     */
	@TableField("article_catgory")
	private String articleCatgory;
    /**
     * 帖子类型
     */
	@TableField("article_catgory_code")
	private String articleCatgoryCode;
    /**
     * 内容
     */
	@TableField("invitation_info")
	private String invitationInfo;
    /**
     * 回复数量
     */
	@TableField("answer_num")
	private Integer answerNum;
    /**
     * 1:显示，0：不显示
     */
	@TableField("invitation_status")
	private Integer invitationStatus;

	@TableField("ok_num")
	private Integer okNum;

	public Integer getOkNum() {
		return okNum;
	}

	public void setOkNum(Integer okNum) {
		this.okNum = okNum;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getInvitationId() {
		return invitationId;
	}

	public void setInvitationId(Long invitationId) {
		this.invitationId = invitationId;
	}

	public String getInvitationImages() {
		return invitationImages;
	}

	public void setInvitationImages(String invitationImages) {
		this.invitationImages = invitationImages;
	}

	public String getUserHeader() {
		return userHeader;
	}

	public void setUserHeader(String userHeader) {
		this.userHeader = userHeader;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getArticleCatgory() {
		return articleCatgory;
	}

	public void setArticleCatgory(String articleCatgory) {
		this.articleCatgory = articleCatgory;
	}

	public String getArticleCatgoryCode() {
		return articleCatgoryCode;
	}

	public void setArticleCatgoryCode(String articleCatgoryCode) {
		this.articleCatgoryCode = articleCatgoryCode;
	}

	public String getInvitationInfo() {
		return invitationInfo;
	}

	public void setInvitationInfo(String invitationInfo) {
		this.invitationInfo = invitationInfo;
	}

	public Integer getAnswerNum() {
		return answerNum;
	}

	public void setAnswerNum(Integer answerNum) {
		this.answerNum = answerNum;
	}

	public Integer getInvitationStatus() {
		return invitationStatus;
	}

	public void setInvitationStatus(Integer invitationStatus) {
		this.invitationStatus = invitationStatus;
	}

	@Override
	public String toString() {
		return "HjInvitation{" +
			", userId=" + userId +
			", invitationId=" + invitationId +
			", invitationImages=" + invitationImages +
			", userHeader=" + userHeader +
			", userName=" + userName +
			", articleCatgory=" + articleCatgory +
			", articleCatgoryCode=" + articleCatgoryCode +
			", invitationInfo=" + invitationInfo +
			", answerNum=" + answerNum +
			", invitationStatus=" + invitationStatus +
			"}";
	}
}

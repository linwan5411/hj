package cn.jeefast.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import cn.jeefast.entity.base.BaseEntity;

/**
 * <p>
 * 回复列表
 * </p>
 *
 * @author zhihang
 * @since 2018-11-21
 */
@TableName("hj_invitation_list")
public class HjInvitationList extends BaseEntity {

    private static final long serialVersionUID = 1L;

	@TableField("comment_id")
    private Long commentId;

	@TableField("invitation_id")
	private Long invitationId;
    /**
     * 用户ID
     */
	@TableField("user_id")
	private Long userId;
    /**
     * 头像
     */
	@TableField("user_header")
	private String userHeader;
    /**
     * 图片地址
     */
	@TableField("invitation_images")
	private String invitationImages;
    /**
     * 回复类容
     */
	@TableField("invitation_info")
	private String invitationInfo;
    /**
     * 点赞数量
     */
	@TableField("answer_num_ok")
	private Integer answerNumOk;
    /**
     * 1:显示，0：不显示
     */
	@TableField("invitation_status")
	private Integer invitationStatus;

	/**
	 * 用户昵称
	 */
	@TableField("user_name")
	private String userName;


	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getInvitationId() {
		return invitationId;
	}

	public void setInvitationId(Long invitationId) {
		this.invitationId = invitationId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserHeader() {
		return userHeader;
	}

	public void setUserHeader(String userHeader) {
		this.userHeader = userHeader;
	}

	public String getInvitationImages() {
		return invitationImages;
	}

	public void setInvitationImages(String invitationImages) {
		this.invitationImages = invitationImages;
	}

	public String getInvitationInfo() {
		return invitationInfo;
	}

	public void setInvitationInfo(String invitationInfo) {
		this.invitationInfo = invitationInfo;
	}

	public Integer getAnswerNumOk() {
		return answerNumOk;
	}

	public void setAnswerNumOk(Integer answerNumOk) {
		this.answerNumOk = answerNumOk;
	}

	public Integer getInvitationStatus() {
		return invitationStatus;
	}

	public void setInvitationStatus(Integer invitationStatus) {
		this.invitationStatus = invitationStatus;
	}

	@Override
	public String toString() {
		return "HjInvitationList{" +
			", invitationId=" + invitationId +
			", userId=" + userId +
			", userHeader=" + userHeader +
			", invitationImages=" + invitationImages +
			", invitationInfo=" + invitationInfo +
			", answerNumOk=" + answerNumOk +
			", invitationStatus=" + invitationStatus +
			"}";
	}
}

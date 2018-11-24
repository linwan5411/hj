package cn.jeefast.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import cn.jeefast.entity.base.BaseEntity;

/**
 * <p>
 * 联系表
 * </p>
 *
 * @author zhihang
 * @since 2018-11-24
 */
@TableName("hj_order")
public class HjOrder extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 联系人ID
     */
	@TableField("from_user_id")
	private Long fromUserId;
    /**
     * 联系人名称
     */
	@TableField("from_user_name")
	private String fromUserName;
    /**
     * 联系人类型
     */
	@TableField("from_user_type")
	private Integer fromUserType;
    /**
     * 联系人电话
     */
	@TableField("from_user_mobile")
	private String fromUserMobile;
    /**
     * 被联系人
     */
	@TableField("to_user_id")
	private Long toUserId;
    /**
     * 被联系人
     */
	@TableField("to_user_name")
	private String toUserName;
    /**
     * 被联系人
     */
	@TableField("to_user_type")
	private Integer toUserType;
    /**
     * 被联系人
     */
	@TableField("to_user_mobile")
	private String toUserMobile;
    /**
     * 需要的服务咨询描述
     */
	@TableField("need_question")
	private String needQuestion;
    /**
     * 0:待处理，1：已处理
     */
	@TableField("opt_status")
	private Integer optStatus;


	public Long getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(Long fromUserId) {
		this.fromUserId = fromUserId;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public Integer getFromUserType() {
		return fromUserType;
	}

	public void setFromUserType(Integer fromUserType) {
		this.fromUserType = fromUserType;
	}

	public String getFromUserMobile() {
		return fromUserMobile;
	}

	public void setFromUserMobile(String fromUserMobile) {
		this.fromUserMobile = fromUserMobile;
	}

	public Long getToUserId() {
		return toUserId;
	}

	public void setToUserId(Long toUserId) {
		this.toUserId = toUserId;
	}

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public Integer getToUserType() {
		return toUserType;
	}

	public void setToUserType(Integer toUserType) {
		this.toUserType = toUserType;
	}

	public String getToUserMobile() {
		return toUserMobile;
	}

	public void setToUserMobile(String toUserMobile) {
		this.toUserMobile = toUserMobile;
	}

	public String getNeedQuestion() {
		return needQuestion;
	}

	public void setNeedQuestion(String needQuestion) {
		this.needQuestion = needQuestion;
	}

	public Integer getOptStatus() {
		return optStatus;
	}

	public void setOptStatus(Integer optStatus) {
		this.optStatus = optStatus;
	}

	@Override
	public String toString() {
		return "HjOrder{" +
			", fromUserId=" + fromUserId +
			", fromUserName=" + fromUserName +
			", fromUserType=" + fromUserType +
			", fromUserMobile=" + fromUserMobile +
			", toUserId=" + toUserId +
			", toUserName=" + toUserName +
			", toUserType=" + toUserType +
			", toUserMobile=" + toUserMobile +
			", needQuestion=" + needQuestion +
			", optStatus=" + optStatus +
			"}";
	}
}

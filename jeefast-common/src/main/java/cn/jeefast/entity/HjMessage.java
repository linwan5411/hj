package cn.jeefast.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import cn.jeefast.entity.base.BaseEntity;

/**
 * <p>
 * 用户的消息通知
 * </p>
 *
 * @author zhihang
 * @since 2018-12-25
 */
@TableName("hj_message")
public class HjMessage extends BaseEntity {

    private static final long serialVersionUID = 1L;

	@TableField("user_id")
	private Long userId;
    /**
     * 1:系统消息，2：特别消息
     */
	@TableField("message_tpye")
	private Integer messageTpye;
	@TableField("mesage_title")
	private String mesageTitle;
    /**
     * 消息内容
     */
	@TableField("message_info")
	private String messageInfo;
    /**
     * 0:未读，1：已读
     */
	@TableField("read_count")
	private Integer readCount;


	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getMessageTpye() {
		return messageTpye;
	}

	public void setMessageTpye(Integer messageTpye) {
		this.messageTpye = messageTpye;
	}

	public String getMesageTitle() {
		return mesageTitle;
	}

	public void setMesageTitle(String mesageTitle) {
		this.mesageTitle = mesageTitle;
	}

	public String getMessageInfo() {
		return messageInfo;
	}

	public void setMessageInfo(String messageInfo) {
		this.messageInfo = messageInfo;
	}

	public Integer getReadCount() {
		return readCount;
	}

	public void setReadCount(Integer readCount) {
		this.readCount = readCount;
	}

	@Override
	public String toString() {
		return "HjMessage{" +
			", userId=" + userId +
			", messageTpye=" + messageTpye +
			", mesageTitle=" + mesageTitle +
			", messageInfo=" + messageInfo +
			", readCount=" + readCount +
			"}";
	}
}

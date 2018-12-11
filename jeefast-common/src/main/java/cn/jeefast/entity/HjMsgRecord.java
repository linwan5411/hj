package cn.jeefast.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import cn.jeefast.entity.base.BaseEntity;

/**
 * <p>
 * 短信记录表
 * </p>
 *
 * @author zhihang
 * @since 2018-11-18
 */
@TableName("hj_msg_record")
public class HjMsgRecord extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户手机号码
     */
	private String mobile;
    /**
     * 验证码
     */
	@TableField("msg_num")
	private String msgNum;
    /**
     * 1：BU业务
     */
	@TableField("msg_type")
	private Integer msgType;


	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMsgNum() {
		return msgNum;
	}

	public void setMsgNum(String msgNum) {
		this.msgNum = msgNum;
	}

	public Integer getMsgType() {
		return msgType;
	}

	public void setMsgType(Integer msgType) {
		this.msgType = msgType;
	}

	@Override
	public String toString() {
		return "HjMsgRecord{" +
			", mobile=" + mobile +
			", msgNum=" + msgNum +
			", msgType=" + msgType +
			"}";
	}
}

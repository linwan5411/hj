package cn.jeefast.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import cn.jeefast.entity.base.BaseEntity;
import com.baomidou.mybatisplus.enums.FieldStrategy;

/**
 * <p>
 * 用户管理表
 * </p>
 *
 * @author zhihang
 * @since 2018-11-18
 */
@TableName("hj_user")
public class HjUser extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 会员ID
     */
	@TableField(value = "user_id",strategy = FieldStrategy.NOT_EMPTY)
	private Long userId;
    /**
     * 手机号码
     */
	@TableField(value ="user_mobile",strategy = FieldStrategy.NOT_EMPTY)
	private String userMobile;
    /**
     * 微信OPEN_ID
     */
	@TableField(value ="open_id",strategy = FieldStrategy.NOT_EMPTY)
	private String openId;
    /**
     * 0:普通，1：农场主，2：服务商
     */
	@TableField(value ="user_type",strategy = FieldStrategy.NOT_EMPTY)
	private Integer userType;
    /**
     * 0:未认证，1：已认证
     */
	@TableField(value ="auth_type",strategy = FieldStrategy.NOT_EMPTY)
	private Integer authType;
    /**
     * 昵称
     */
	@TableField(value ="user_name",strategy = FieldStrategy.NOT_EMPTY)
	private String userName;
    /**
     * 1:启用，0：禁用
     */
	@TableField(value ="data_status",strategy = FieldStrategy.NOT_EMPTY)
	private Integer dataStatus;
    /**
     * 头像
     */
	@TableField(value ="user_portrait",strategy = FieldStrategy.NOT_EMPTY)
	private String userPortrait;
    /**
     * 登陆密码
     */
	@TableField(value ="login_pwd",strategy = FieldStrategy.NOT_EMPTY)
	private String loginPwd;
    /**
     * 密码盐
     */
	@TableField(value ="login_salt",strategy = FieldStrategy.NOT_EMPTY)
	private String loginSalt;
    /**
     * 最后登陆时间
     */
	@TableField(value ="last_login_time",strategy = FieldStrategy.NOT_EMPTY)
	private Date lastLoginTime;


	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Integer getAuthType() {
		return authType;
	}

	public void setAuthType(Integer authType) {
		this.authType = authType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getDataStatus() {
		return dataStatus;
	}

	public void setDataStatus(Integer dataStatus) {
		this.dataStatus = dataStatus;
	}

	public String getUserPortrait() {
		return userPortrait;
	}

	public void setUserPortrait(String userPortrait) {
		this.userPortrait = userPortrait;
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	public String getLoginSalt() {
		return loginSalt;
	}

	public void setLoginSalt(String loginSalt) {
		this.loginSalt = loginSalt;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	@Override
	public String toString() {
		return "HjUser{" +
			", userId=" + userId +
			", userMobile=" + userMobile +
			", openId=" + openId +
			", userType=" + userType +
			", authType=" + authType +
			", userName=" + userName +
			", dataStatus=" + dataStatus +
			", userPortrait=" + userPortrait +
			", loginPwd=" + loginPwd +
			", loginSalt=" + loginSalt +
			", lastLoginTime=" + lastLoginTime +
			"}";
	}
}

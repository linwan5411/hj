package cn.jeefast.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import cn.jeefast.entity.base.BaseEntity;

/**
 * <p>
 * 服务商的介绍
 * </p>
 *
 * @author zhihang
 * @since 2018-11-20
 */
@TableName("hj_server_remak")
public class HjServerRemak extends BaseEntity {

    private static final long serialVersionUID = 1L;

	@TableField("server_id")
	private Long serverId;
    /**
     * 介绍文字描述
     */
	@TableField("server_info")
	private String serverInfo;
    /**
     * 1:显示
     */
	@TableField("server_show")
	private Integer serverShow;
    /**
     * 图片
     */
	@TableField("server_image")
	private String serverImage;


	public Long getServerId() {
		return serverId;
	}

	public void setServerId(Long serverId) {
		this.serverId = serverId;
	}

	public String getServerInfo() {
		return serverInfo;
	}

	public void setServerInfo(String serverInfo) {
		this.serverInfo = serverInfo;
	}

	public Integer getServerShow() {
		return serverShow;
	}

	public void setServerShow(Integer serverShow) {
		this.serverShow = serverShow;
	}

	public String getServerImage() {
		return serverImage;
	}

	public void setServerImage(String serverImage) {
		this.serverImage = serverImage;
	}

	@Override
	public String toString() {
		return "HjServerRemak{" +
			", serverId=" + serverId +
			", serverInfo=" + serverInfo +
			", serverShow=" + serverShow +
			", serverImage=" + serverImage +
			"}";
	}
}

package cn.jeefast.rest.entity.vo;

import com.baomidou.mybatisplus.annotations.TableField;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 服务商认证对象
 */
public class ServerAuthVo extends TokenVo {

    private static final long serialVersionUID = -5087852319042682538L;

    @ApiModelProperty(value="服务商ID", hidden=false,  required=true, dataType="String")
    private Long serverId;

    @ApiModelProperty(value="认证类型", hidden=false,  required=true, dataType="String")
    private Integer serverType;

    @ApiModelProperty(value="服务商名称", hidden=false,  required=true, dataType="String")
    private String companyName;

    @ApiModelProperty(value="联系电话", hidden=false,  required=true, dataType="String")
    private String linkPhone;

    @ApiModelProperty(value="联系人", hidden=false,  required=true, dataType="String")
    private String linkName;

    @ApiModelProperty(value="服务的类型，多个用逗号隔开", hidden=false,  required=true, dataType="String")
    private String serverCodes;

    @ApiModelProperty(value="经营范围", hidden=false,  required=false, dataType="String")
    private String companyScope;

    @ApiModelProperty(value="营业执照图片地址", hidden=false,  required=false, dataType="String")
    private String serverRegImage;

    @ApiModelProperty(value="注册资本", hidden=false,  required=false, dataType="String")
    private String registCapital;

    @ApiModelProperty(value="详细地址", hidden=false,  required=true, dataType="String")
    private String detailAddr;

    @ApiModelProperty(value="省", hidden=false,  required=true, dataType="String")
    private Long provice;

    @ApiModelProperty(value="市", hidden=false,  required=true, dataType="String")
    private Long city;

    @ApiModelProperty(value="区", hidden=false,  required=true, dataType="String")
    private Long district;

    @ApiModelProperty(value="纬度", hidden=false,  required=true, dataType="Double")
    private Double latitude;

    @ApiModelProperty(value="经度", hidden=false,  required=true, dataType="Double")
    private Double longitude;

    @ApiModelProperty(value="文字介绍", hidden=false,  required=true, dataType="JSON")
    private List<ServerRemakVo> remarkList;

    @ApiModelProperty(value="服务等级", hidden=false,  required=true, dataType="String")
    private String serverGrade;

    @ApiModelProperty(value="服务的类型", hidden=false,  required=true, dataType="String")
    private String serverGenre;

    @ApiModelProperty(value="可以托管的服务区域,用逗号分隔", hidden=false,  required=true, dataType="String")
    private String trusteeshipList;

    @ApiModelProperty(value="服务的数量", hidden=false,  required=true, dataType="String")
    private Integer clientNum = 0;

    public Integer getClientNum() {
        return clientNum;
    }

    public void setClientNum(Integer clientNum) {
        this.clientNum = clientNum;
    }

    public String getServerGrade() {
        return serverGrade;
    }

    public void setServerGrade(String serverGrade) {
        this.serverGrade = serverGrade;
    }

    public String getServerGenre() {
        return serverGenre;
    }

    public void setServerGenre(String serverGenre) {
        this.serverGenre = serverGenre;
    }

    public String getTrusteeshipList() {
        return trusteeshipList;
    }

    public void setTrusteeshipList(String trusteeshipList) {
        this.trusteeshipList = trusteeshipList;
    }

    public Long getServerId() {
        return serverId;
    }

    public void setServerId(Long serverId) {
        this.serverId = serverId;
    }

    public Integer getServerType() {
        return serverType;
    }

    public void setServerType(Integer serverType) {
        this.serverType = serverType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLinkPhone() {
        return linkPhone;
    }

    public void setLinkPhone(String linkPhone) {
        this.linkPhone = linkPhone;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public String getServerCodes() {
        return serverCodes;
    }

    public void setServerCodes(String serverCodes) {
        this.serverCodes = serverCodes;
    }

    public String getCompanyScope() {
        return companyScope;
    }

    public void setCompanyScope(String companyScope) {
        this.companyScope = companyScope;
    }

    public String getServerRegImage() {
        return serverRegImage;
    }

    public void setServerRegImage(String serverRegImage) {
        this.serverRegImage = serverRegImage;
    }

    public String getRegistCapital() {
        return registCapital;
    }

    public void setRegistCapital(String registCapital) {
        this.registCapital = registCapital;
    }

    public String getDetailAddr() {
        return detailAddr;
    }

    public void setDetailAddr(String detailAddr) {
        this.detailAddr = detailAddr;
    }

    public Long getProvice() {
        return provice;
    }

    public void setProvice(Long provice) {
        this.provice = provice;
    }

    public Long getCity() {
        return city;
    }

    public void setCity(Long city) {
        this.city = city;
    }

    public Long getDistrict() {
        return district;
    }

    public void setDistrict(Long district) {
        this.district = district;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public List<ServerRemakVo> getRemarkList() {
        return remarkList;
    }

    public void setRemarkList(List<ServerRemakVo> remarkList) {
        this.remarkList = remarkList;
    }
}

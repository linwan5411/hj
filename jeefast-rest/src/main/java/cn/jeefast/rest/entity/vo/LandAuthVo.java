package cn.jeefast.rest.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 添加土地的操作
 */
public class LandAuthVo extends TokenVo {

    private static final long serialVersionUID = -5087852319042682538L;

    @ApiModelProperty(value="土地ID", hidden=false,  required=true, dataType="String")
    private Long haciendaId;

    @ApiModelProperty(value="附着物种类", hidden=false,  required=true, dataType="String")
    private String haciendaScope;

    @ApiModelProperty(value="土地面积", hidden=false,  required=true, dataType="String")
    private Double serverMax;

    @ApiModelProperty(value="土地类型", hidden=false,  required=true, dataType="String")
    private String haciendaGener;

    @ApiModelProperty(value="联系电话", hidden=false,  required=true, dataType="String")
    private String linkPhone;

    @ApiModelProperty(value="联系人", hidden=false,  required=true, dataType="String")
    private String linkName;

    @ApiModelProperty(value="土地性质编码", hidden=false,  required=true, dataType="String")
    private String serverCategory;

    @ApiModelProperty(value="所需服务编码", hidden=false,  required=true, dataType="String")
    private String needServer;

    @ApiModelProperty(value="营业执照图片地址", hidden=false,  required=false, dataType="String")
    private String haciendaRegImage;

    @ApiModelProperty(value="简介", hidden=false,  required=false, dataType="String")
    private String haciendaRemark;

    @ApiModelProperty(value="类型,1:个人；2：企业", hidden=false,  required=true, dataType="String")
    private Integer haciendaType;

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

    @ApiModelProperty(value="土地介绍", hidden=false,  required=true, dataType="JSON")
    private List<LandRemakVo> remarkList;

    public String getHaciendaGener() {
        return haciendaGener;
    }

    public void setHaciendaGener(String haciendaGener) {
        this.haciendaGener = haciendaGener;
    }

    public List<LandRemakVo> getRemarkList() {
        return remarkList;
    }

    public String getNeedServer() {
        return needServer;
    }

    public void setNeedServer(String needServer) {
        this.needServer = needServer;
    }

    public void setRemarkList(List<LandRemakVo> remarkList) {
        this.remarkList = remarkList;
    }

    public Long getHaciendaId() {
        return haciendaId;
    }

    public void setHaciendaId(Long haciendaId) {
        this.haciendaId = haciendaId;
    }

    public String getHaciendaScope() {
        return haciendaScope;
    }

    public void setHaciendaScope(String haciendaScope) {
        this.haciendaScope = haciendaScope;
    }

    public Double getServerMax() {
        return serverMax;
    }

    public void setServerMax(Double serverMax) {
        this.serverMax = serverMax;
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

    public String getServerCategory() {
        return serverCategory;
    }

    public void setServerCategory(String serverCategory) {
        this.serverCategory = serverCategory;
    }

    public String getHaciendaRegImage() {
        return haciendaRegImage;
    }

    public void setHaciendaRegImage(String haciendaRegImage) {
        this.haciendaRegImage = haciendaRegImage;
    }

    public String getHaciendaRemark() {
        return haciendaRemark;
    }

    public void setHaciendaRemark(String haciendaRemark) {
        this.haciendaRemark = haciendaRemark;
    }

    public Integer getHaciendaType() {
        return haciendaType;
    }

    public void setHaciendaType(Integer haciendaType) {
        this.haciendaType = haciendaType;
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
}

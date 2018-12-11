package cn.jeefast.vo;


import java.io.Serializable;

public class AreaVo implements Serializable {

    /**
     * 地区Id
     */
    private Long areaId;
    /**
     * 地区编码
     */
    private String areaCode;
    /**
     * 地区名
     */
    private String areaName;

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
}

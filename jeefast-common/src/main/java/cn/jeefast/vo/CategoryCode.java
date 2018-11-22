package cn.jeefast.vo;

import java.io.Serializable;

public class CategoryCode implements Serializable{
    private static final long serialVersionUID = -3239330074787036692L;
    private String serverCategory;
    private String serverIcon;
    private String categoryCode;
    private String serverRemark;

    public String getServerCategory() {
        return serverCategory;
    }

    public void setServerCategory(String serverCategory) {
        this.serverCategory = serverCategory;
    }

    public String getServerIcon() {
        return serverIcon;
    }

    public void setServerIcon(String serverIcon) {
        this.serverIcon = serverIcon;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getServerRemark() {
        return serverRemark;
    }

    public void setServerRemark(String serverRemark) {
        this.serverRemark = serverRemark;
    }
}

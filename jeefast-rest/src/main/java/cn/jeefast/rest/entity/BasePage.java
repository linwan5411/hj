package cn.jeefast.rest.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class BasePage implements Serializable {

    @ApiModelProperty(value="开始页面", hidden=false,  required=true, dataType="Long")
    private Integer page = 1;

    @ApiModelProperty(value="每页显示条数", hidden=false,  required=true, dataType="Long")
    private Integer pageSize = 10;

    @ApiModelProperty(value="偏移量", hidden=true,  required=true, dataType="Long")
    private Integer pageIndex = 0;

    public Integer getPage() {
        if(page == null || page <= 0)
            return 1;
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        if(pageSize == null || pageSize <= 0)
            return 10;
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageIndex() {
        return (getPage() - 1) * getPageSize();
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }
}

package cn.jeefast.entity.base;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.FieldStrategy;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <pre>
 * <b>.</b>
 * <b>Description:</b>
 *
 * <b>Author:zhihang</b>
 * <b>Date: 2018/11/17 0017 14:56   </b>
 * <b>Copyright:</b> Copyright 2008-2026 http://www.jinvovo.com Technology Co., Ltd. All rights reserved.
 * <b>Changelog:</b>
 *   ----------------------------------------------------------------------------
 *   Ver    Date                     Author                        Detail
 *   ----------------------------------------------------------------------------
 *   1.0   2018/11/17 0017 14:56          zhihang            new file.
 * <pre>
 */
public class BaseEntity implements Serializable{

    @TableId(value="id", type= IdType.AUTO)
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    @TableField(value = "create_time",strategy = FieldStrategy.NOT_EMPTY)
    private Date createTime;

    @TableField(value = "update_time",strategy = FieldStrategy.NOT_EMPTY)
    private Date updateTime;

    @TableField(value = "data_version",strategy = FieldStrategy.NOT_EMPTY)
    private Integer dataVersion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDataVersion() {
        return dataVersion;
    }

    public void setDataVersion(Integer dataVersion) {
        this.dataVersion = dataVersion;
    }
}

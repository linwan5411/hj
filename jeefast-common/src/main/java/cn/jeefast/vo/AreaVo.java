package cn.jeefast.vo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
public class AreaVo implements Serializable {

    /**
     * 地区Id
     */
    private Long areaId;
    /**
     * 地区编码
     */
    @JsonIgnore
    private String areaCode;
    /**
     * 地区名
     */
    private String areaName;

}

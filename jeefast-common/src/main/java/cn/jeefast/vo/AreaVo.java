package cn.jeefast.vo;


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
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String areaCode;
    /**
     * 地区名
     */
    private String areaName;

}

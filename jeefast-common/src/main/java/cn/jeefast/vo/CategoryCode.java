package cn.jeefast.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryCode implements Serializable{
    private static final long serialVersionUID = -3239330074787036692L;
    private String serverCategory;
    private String serverIcon;
    @JsonIgnore
    private Long parentId;
    private String categoryCode;
}

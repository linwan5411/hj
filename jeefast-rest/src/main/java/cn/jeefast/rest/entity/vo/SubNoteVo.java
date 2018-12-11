package cn.jeefast.rest.entity.vo;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * 发表话题
 */
public class SubNoteVo extends TokenVo {
    private static final long serialVersionUID = 3858985501607274940L;

    @ApiModelProperty(value="话题内容不能为空", hidden=false,  required=true, dataType="String")
    @NotNull(message = "话题内容不能为空")
    private String notComment;

    @ApiModelProperty(value="图片的列表以逗号分隔", hidden=false,  required=true, dataType="String")
    private String imageList;

    @ApiModelProperty(value="话题内容不能为空", hidden=false,  required=true, dataType="String")
    @NotNull(message = "话题类型不能为空")
    private String noteType;

    public String getNotComment() {
        return notComment;
    }

    public void setNotComment(String notComment) {
        this.notComment = notComment;
    }

    public String getImageList() {
        return imageList;
    }

    public void setImageList(String imageList) {
        this.imageList = imageList;
    }

    public String getNoteType() {
        return noteType;
    }

    public void setNoteType(String noteType) {
        this.noteType = noteType;
    }
}

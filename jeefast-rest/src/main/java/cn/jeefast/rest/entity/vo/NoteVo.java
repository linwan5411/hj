package cn.jeefast.rest.entity.vo;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * 评论
 */
public class NoteVo extends TokenVo {
    private static final long serialVersionUID = 3858985501607274940L;

    @ApiModelProperty(value="帖子ID", hidden=false,  required=true, dataType="Long")
    private Long invitationId;

    @ApiModelProperty(value="评论的内容", hidden=false,  required=true, dataType="String")
    @NotNull(message = "评论内容不能为空")
    private String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getInvitationId() {
        return invitationId;
    }

    public void setInvitationId(Long invitationId) {
        this.invitationId = invitationId;
    }
}

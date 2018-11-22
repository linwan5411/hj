package cn.jeefast.rest.controller;

import cn.jeefast.common.utils.BaseResponse;
import cn.jeefast.common.utils.ResultUtils;
import cn.jeefast.common.utils.TokenUtil;
import cn.jeefast.entity.HjUser;
import cn.jeefast.rest.entity.BasePage;
import cn.jeefast.rest.entity.vo.NoteVo;
import cn.jeefast.service.HjArticleService;
import cn.jeefast.service.HjInvitationListService;
import cn.jeefast.service.HjInvitationService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 文章
 */
@RestController
@RequestMapping("/api/v1")
public class ApiNoteController {

    @Resource
    private HjInvitationService hjInvitationService;

    @Resource
    private HjInvitationListService hjInvitationListService;

    @ApiOperation(value = "查询话题")
    @PostMapping("/findAdNote")
    public BaseResponse findAdNote(@RequestBody BasePage basePage){
        return ResultUtils.successV2(hjInvitationService.findAdNote(basePage.getPageIndex(),basePage.getPage()));
    }

    @ApiOperation(value = "查询话题详情")
    @PostMapping("/findAdNoteInfo/{noteId}")
    public BaseResponse findAdNoteInfo(@PathVariable("noteId")Long noteId){
        return ResultUtils.successV2(hjInvitationService.findAdNoteInfo(noteId));
    }

    @ApiOperation(value = "话题评论列表")
    @PostMapping("/findNoteInvitation/{noteId}")
    public BaseResponse findNoteInvitation(@PathVariable("noteId")Long noteId,@RequestBody BasePage basePage){
        return ResultUtils.successV2(hjInvitationListService.findNoteInvitation(noteId,basePage.getPageIndex(),basePage.getPageSize()));
    }

    @ApiOperation(value = "话题阅读量")
    @PostMapping("/noteOk/{noteId}")
    public BaseResponse noteOk(@PathVariable("noteId")Long noteId){
        hjInvitationService.noteOk(noteId);
        return ResultUtils.successV2();
    }

    //TODO 还有2个接口未实现

    @ApiOperation(value = "发表评论")
    @PostMapping("/doComment")
    public BaseResponse doComment(@Valid @RequestBody NoteVo noteVo){
        HjUser user = TokenUtil.parseUser(noteVo.getToken());
        hjInvitationListService.doComment(user,noteVo.getInvitationId(),noteVo.getComment());
        return ResultUtils.successV2();
    }

    @ApiOperation(value = "发表评论顶赞")
    @PostMapping("/noteOk/{commentId}")
    public BaseResponse commentIdOk(@PathVariable("commentId")Long commentId){
        hjInvitationListService.commentIdOk(commentId);
        return ResultUtils.successV2();
    }

}

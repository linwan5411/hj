package cn.jeefast.web;

import cn.jeefast.base.BaseController;
import cn.jeefast.common.utils.BaseResponse;
import cn.jeefast.common.utils.JsonUtils;
import cn.jeefast.common.utils.ResultUtils;
import cn.jeefast.entity.HjInvitation;
import cn.jeefast.entity.HjInvitationList;
import cn.jeefast.service.HjArticleService;
import cn.jeefast.service.HjInvitationListService;
import cn.jeefast.service.HjInvitationService;
import cn.jeefast.service.HomeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 问题列表
 */
@Controller
public class NoteHomeController extends BaseController {


    @Resource
    private HjInvitationService hjInvitationService;

    @Resource
    private HjInvitationListService hjInvitationListService;

    /**
     * 帖子列表
     *
     * @return
     */
    @RequestMapping(value = "/noteList")
    public ModelAndView noteList() {
        Map<String, Object> map = new HashMap<>();
        List<HjInvitation> list = hjInvitationService.findAdNote(0, 10);
        map.put("list", list);
        System.out.println(JsonUtils.Bean2Json(map));
        return new ModelAndView("noteList", map);
    }

    /**
     * 帖子详情
     *
     * @return
     */
    @RequestMapping(value = "/noteInfo/{noteId}")
    public ModelAndView noteInfo(@PathVariable("noteId")Long noteId) {
        Map<String, Object> map = new HashMap<>();
        //帖子
        HjInvitation info = hjInvitationService.findAdNoteInfo(noteId);
        map.put("info", info);

        List<HjInvitationList> list = hjInvitationListService.findNoteInvitation(noteId,0,10);
        map.put("list", list);

        //条数
        Integer num = hjInvitationListService.findListNum(noteId);
        map.put("num", num);

        hjInvitationListService.doReadNum(noteId);

        System.out.println(JsonUtils.Bean2Json(map));
        return new ModelAndView("noteInfo", map);
    }

    /**
     * 帖子点赞
     * @param noteId
     * @return
     */
    @RequestMapping(value = "/noteOk/{noteId}")
    public BaseResponse noteOk(@PathVariable("noteId")Long noteId) {
        hjInvitationService.noteOk(noteId);
        return ResultUtils.successV2();
    }

    /**
     * 帖子点赞
     * @param noteId
     * @return
     */
    @RequestMapping(value = "/noteListOk/{noteId}")
    public BaseResponse noteListOk(@PathVariable("noteId")Long noteId) {
        hjInvitationListService.commentIdOk(noteId);
        return ResultUtils.successV2();
    }
}

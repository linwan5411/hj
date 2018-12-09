package cn.jeefast.web;

import cn.jeefast.base.BaseController;
import cn.jeefast.common.utils.JsonUtils;
import cn.jeefast.entity.HjHaciendaInfo;
import cn.jeefast.service.HjAreaService;
import cn.jeefast.service.HjHaciendaInfoService;
import cn.jeefast.service.HjInvitationListService;
import cn.jeefast.service.HjServerInfoService;
import cn.jeefast.vo.AreaLntGntVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 农场主
 */
@Controller
public class LandListController extends BaseController{

    @Resource
    private HjAreaService hjAreaService;

    @Resource
    private HjHaciendaInfoService hjHaciendaInfoService;

    /**
     * 主页数据的返回
     * @param request
     * @return
     */
    @RequestMapping(value = "/landList")
    public ModelAndView serverList(HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        String code = BaseController.getPriviceCode(request);
        setAreaCode(code,request);
        AreaLntGntVo vo = hjAreaService.findByCodeVo(code);
        List<Map<String,Object>> list = hjHaciendaInfoService.findLandMore(vo.getLng(), vo.getLat(), null,
                null, null, 0, 10, null);
        map.put("list",list);
        System.out.println(JsonUtils.Bean2Json(list));
        return new ModelAndView("landList",map);
    }

    /**
     * 农场主详情
     * @return
     */
    @RequestMapping(value = "/landInfo/{landId}")
    public ModelAndView landInfo(@PathVariable("landId")Long landId){
        Map<String,Object> map = new HashMap<>();
        HjHaciendaInfo info = hjHaciendaInfoService.findLandDetail(landId);
        map.put("info",info);
        System.out.println(JsonUtils.Bean2Json(info));
        return new ModelAndView("landInfo",map);
    }


    /**
     * 主页数据的返回
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/ajaxLandListMore")
    public List<Map<String,Object>> ajaxLandListMore(HttpServletRequest request){
        String code = BaseController.getPriviceCode(request);
        setAreaCode(code,request);
        AreaLntGntVo vo = hjAreaService.findByCodeVo(code);
        Long areaId = null;
        Integer authType = null;
        Integer userType = null;
        Integer pageIndex = null;
        String categoryCode = null;
        List<Map<String,Object>> list = hjHaciendaInfoService.findLandMore(vo.getLng(), vo.getLat(), areaId, authType, userType, pageIndex, 10, categoryCode);
        return list;
    }

}

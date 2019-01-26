package cn.jeefast.web;

import cn.jeefast.base.BaseController;
import cn.jeefast.common.utils.JsonUtils;
import cn.jeefast.entity.HjHaciendaInfo;
import cn.jeefast.service.HjAreaService;
import cn.jeefast.service.HjHaciendaInfoService;
import cn.jeefast.vo.AreaLntGntVo;
import org.apache.commons.lang.StringUtils;
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
@RequestMapping(value = "/wx")
public class WxLandListController extends BaseController{

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
        return new ModelAndView("wx/wx_landList",map);
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
        return new ModelAndView("wx/wx_landInfo",map);
    }


    /**
     * 主页数据的返回
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/ajaxLandListMore")
    public List<Map<String,Object>> ajaxLandListMore(Integer authType,Integer userType,String categoryCode,String areaCode,Integer page,HttpServletRequest request){
        if(StringUtils.isBlank(categoryCode)){
            categoryCode = null;
        }
        if(page == null){
            page = 2;
        }
        if(!StringUtils.isNotBlank(areaCode)){
            areaCode =  BaseController.getPriviceCode(request);
        }
        setAreaCode(areaCode,request);
        AreaLntGntVo vo = hjAreaService.findByCodeVo(areaCode);
        List<Map<String,Object>> list = hjHaciendaInfoService.findLandMore(vo.getLng(), vo.getLat(), vo.getAreaId(), authType, userType,
                (page - 1) * 10, 10, categoryCode);
        return list;
    }

}

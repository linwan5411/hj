package cn.jeefast.web;

import cn.jeefast.base.BaseController;
import cn.jeefast.common.utils.JsonUtils;
import cn.jeefast.entity.HjServerCase;
import cn.jeefast.entity.HjServerInfo;
import cn.jeefast.service.HjAreaService;
import cn.jeefast.service.HjServerCaseService;
import cn.jeefast.service.HjServerInfoService;
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
 * 服务商列表页面
 */
@Controller
@RequestMapping(value = "/wx")
public class WxServerListController extends BaseController{

    @Resource
    private HjAreaService hjAreaService;

    @Resource
    private HjServerInfoService hjServerInfoService;

    @Resource
    private HjServerCaseService hjServerCaseService;

    /**
     * 服务商列表页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/serverList")
    public ModelAndView serverList(HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        String code = BaseController.getPriviceCode(request);
        setAreaCode(code,request);

        AreaLntGntVo area = hjAreaService.findByCodeVo(code);
        List<Map<String,Object>> list = hjServerInfoService.findServerMore(area.getLat(),area.getLng(),null,
                null,null,0,10,null);
        map.put("list",list);
        System.out.println(JsonUtils.Bean2Json(list));
        return new ModelAndView("wx/wx_severList",map);
    }


    /**
     * 案例详情
     * @return
     */
    @RequestMapping(value = "/caseInfo/{caseId}")
    public ModelAndView caseInfo(@PathVariable("caseId") Long caseId){
        Map<String,Object> map = new HashMap<>();
        HjServerCase info = hjServerCaseService.findCase(Long.valueOf(caseId));
        map.put("info",info);
        System.out.println(JsonUtils.Bean2Json(info));
        return new ModelAndView("wx/wx_severCaseInfo",map);
    }


    /**
     * 服务商详情
     * @return
     */
    @RequestMapping(value = "/severInfo/{serverId}")
    public ModelAndView serverList(@PathVariable("serverId") Long serverId){
        Map<String,Object> map = new HashMap<>();
        HjServerInfo info = hjServerInfoService.findServerDetail(serverId);
        map.put("info",info);
        System.out.println(JsonUtils.Bean2Json(info));
        return new ModelAndView("wx/wx_severInfo",map);
    }

    /**
     * 查询列表的操作
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/ajaxListMore")
    public List<Map<String,Object>> listMore(Integer authType,Integer userType,String categoryCode,String areaCode,Integer page,HttpServletRequest request){
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

        AreaLntGntVo area = hjAreaService.findByCodeVo(areaCode);
        List<Map<String,Object>> list = hjServerInfoService.findServerMore(area.getLat(),area.getLng(),area.getAreaId(),
                authType,userType,(page - 1) * 10,10,categoryCode);
        return list;
    }



}

package cn.jeefast.web;

import cn.jeefast.base.BaseController;
import cn.jeefast.common.utils.JsonUtils;
import cn.jeefast.entity.HjArea;
import cn.jeefast.entity.HjServerCase;
import cn.jeefast.entity.HjServerInfo;
import cn.jeefast.service.HjAreaService;
import cn.jeefast.service.HjServerCaseService;
import cn.jeefast.service.HjServerInfoService;
import cn.jeefast.service.HomeService;
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
 * 服务商列表页面
 */
@Controller
public class ServerListController extends BaseController{

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
        return new ModelAndView("severList",map);
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
        return new ModelAndView("severCaseInfo",map);
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
        return new ModelAndView("severInfo",map);
    }

    /**
     * 查询列表的操作
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/ajaxListMore")
    public List<Map<String,Object>> listMore(HttpServletRequest request){
        Integer authTyppe = null;
        Integer userTyppe = null;
        String categoryCode = null;
        Long areaId = null;
        Integer page = 2;

        String code = BaseController.getPriviceCode(request);
        setAreaCode(code,request);

        AreaLntGntVo area = hjAreaService.findByCodeVo(code);
        List<Map<String,Object>> list = hjServerInfoService.findServerMore(area.getLat(),area.getLng(),areaId,
                null,null,page,10,null);
        return list;
    }



}

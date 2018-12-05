package cn.jeefast.web;

import cn.jeefast.base.BaseController;
import cn.jeefast.common.utils.JsonUtils;
import cn.jeefast.entity.HjArea;
import cn.jeefast.service.HjAreaService;
import cn.jeefast.service.HjServerInfoService;
import cn.jeefast.service.HomeService;
import cn.jeefast.vo.AreaLntGntVo;
import org.springframework.stereotype.Controller;
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

    /**
     * 主页数据的返回
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
     * 查询列表的操作
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/listMore")
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

package cn.jeefast.web;

import cn.jeefast.base.BaseController;
import cn.jeefast.common.utils.JsonUtils;
import cn.jeefast.entity.HjArea;
import cn.jeefast.service.HjAreaService;
import cn.jeefast.service.HomeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 主页
 */
@Controller
public class HomeController extends BaseController{

    /**
     * 主页数据的返回
     * @param request
     * @return
     */
    @RequestMapping(value = "/")
    public ModelAndView home(HttpServletRequest request){
        return new ModelAndView("index");
    }

}

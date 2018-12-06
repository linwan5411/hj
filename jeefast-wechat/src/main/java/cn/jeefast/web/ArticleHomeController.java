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
import java.util.Map;

/**
 * 文章主页
 */
@Controller
public class ArticleHomeController extends BaseController{

    @Resource
    private HomeService homeService;

    /**
     * 主页数据的返回
     * @return
     */
    @RequestMapping(value = "/articleHome")
    public ModelAndView articleHome(){
        Map<String,Object> map = homeService.articleHomeIndex();
        System.out.println(JsonUtils.Bean2Json(map));
        return new ModelAndView("articlehome",map);
    }


}

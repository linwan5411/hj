package cn.jeefast.web;

import cn.jeefast.base.BaseController;
import cn.jeefast.common.utils.BaseResponse;
import cn.jeefast.common.utils.JsonUtils;
import cn.jeefast.common.utils.ResultUtils;
import cn.jeefast.entity.HjArea;
import cn.jeefast.entity.HjArticle;
import cn.jeefast.entity.HjInvitationList;
import cn.jeefast.service.HjAreaService;
import cn.jeefast.service.HjArticleService;
import cn.jeefast.service.HjHaciendaInfoService;
import cn.jeefast.service.HomeService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
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
 * 文章主页
 */
@Controller
public class ArticleHomeController extends BaseController{

    @Resource
    private HomeService homeService;

    @Resource
    private HjArticleService hjArticleService;

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

    /**
     * 文章列表数据
     * @return
     */
    @RequestMapping(value = "/articleList")
    public ModelAndView articleList(){
        Map<String,Object> map = new HashMap<>();
        List<Map<String,Object>> list = hjArticleService.findAdArticle(0,10);
        map.put("list",list);
        System.out.println(JsonUtils.Bean2Json(map));
        return new ModelAndView("articleList",map);
    }

    /**
     * 文章详情
     * @return
     */
    @RequestMapping(value = "/articleInfo/{articleId}")
    public ModelAndView articleInfo(@PathVariable("articleId")Long articleId){
        Map<String,Object> map = new HashMap<>();

        //文章详情
        EntityWrapper wrapper = new EntityWrapper();
        wrapper.where("article_id={0}",articleId);
        HjArticle article = hjArticleService.selectOne(wrapper);
        map.put("article",article);

        List<Map<String,Object>> list = hjArticleService.findLikeArticle(articleId);
        map.put("list",list);
        hjArticleService.articleLight(articleId);
        System.out.println(JsonUtils.Bean2Json(map));
        return new ModelAndView("articleInfo",map);
    }

}

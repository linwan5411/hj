package cn.jeefast.rest.controller;

import cn.jeefast.common.utils.BaseResponse;
import cn.jeefast.common.utils.ResultUtils;
import cn.jeefast.rest.entity.BasePage;
import cn.jeefast.rest.entity.vo.AdSiteVo;
import cn.jeefast.rest.entity.vo.AddrReqVo;
import cn.jeefast.rest.entity.vo.CategoryVo;
import cn.jeefast.service.HjAdService;
import cn.jeefast.service.HjAreaService;
import cn.jeefast.service.HjArticleService;
import cn.jeefast.service.HjServerCodeService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 文章
 */
@RestController
@RequestMapping("/api/v1")
public class ApiArticleController {

    @Resource
    private HjArticleService hjArticleService;


    @ApiOperation(value = "查询文章列表")
    @PostMapping("/findAdArticle")
    public BaseResponse findAdArticle(@RequestBody BasePage basePage){
        return ResultUtils.successV2(hjArticleService.findAdArticle(basePage.getPageIndex(),basePage.getPage()));
    }

    @ApiOperation(value = "推荐首页文章")
    @PostMapping("/findHomeArticle")
    public BaseResponse findHomeArticle(){
        return ResultUtils.successV2(hjArticleService.findHomeArticle(3));
    }

    @ApiOperation(value = "文章详情")
    @PostMapping("/findHomeArticle/{articleId}")
    public BaseResponse findArticleInfo(@PathVariable("articleId")Long articleId){
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.where("article_id={0}",articleId);
        return ResultUtils.successV2(hjArticleService.selectOne(entityWrapper));
    }

    @ApiOperation(value = "猜喜欢")
    @PostMapping("/findLikeArticle/{articleId}")
    public BaseResponse findLikeArticle(@PathVariable("articleId")Long articleId){
        return ResultUtils.successV2(hjArticleService.findLikeArticle(articleId));
    }

}

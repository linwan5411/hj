package cn.jeefast.service.impl;

import cn.jeefast.config.redis.Cacheable;
import cn.jeefast.entity.HjArticle;
import cn.jeefast.dao.HjArticleDao;
import cn.jeefast.service.HjArticleService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 文章 服务实现类
 * </p>
 *
 * @author zhihang
 * @since 2018-11-21
 */
@Service
public class HjArticleServiceImpl extends ServiceImpl<HjArticleDao, HjArticle> implements HjArticleService {

    @Resource
    private HjArticleDao hjArticleDao;

    @Override
    public List<Map<String,Object>> findAdArticle(Integer pageIndex, Integer pageSize,String categoryCode) {
        if(StringUtils.isBlank(categoryCode)){
            categoryCode = null;
        }
        return hjArticleDao.findAdArticle(pageIndex,pageSize,categoryCode);
    }

    @Override
    public List<Map<String,Object>> findLikeArticle(Long articleId) {
        Long code = null;
        HjArticle article = new HjArticle();article.setArticleId(articleId);
        article = hjArticleDao.selectOne(article);
        if(article != null && article.getArticleCategory() != null){
            code = article.getArticleCategory();
        }
        return hjArticleDao.findLikeArticle(code,5,article.getArticleId());
    }

    @Cacheable(key = "homeArticle",fieldKey = "#pageSize")
    @Override
    public List<Map<String, Object>> findHomeArticle(Integer pageSize) {
        return hjArticleDao.findAdArticle(0,pageSize,null);
    }

    @Async
    @Override
    public void articleLight(Long articleId) {
        try {
            hjArticleDao.articleLight(articleId);
        }catch (Exception e){

        }
    }
}

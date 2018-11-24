package cn.jeefast.service;

import cn.jeefast.entity.HjArticle;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 文章 服务类
 * </p>
 *
 * @author zhihang
 * @since 2018-11-21
 */
public interface HjArticleService extends IService<HjArticle> {

    List<Map<String,Object>> findAdArticle(Integer pageIndex, Integer pageSzie);

    List<Map<String,Object>> findLikeArticle(Long articleId);

    /**
     * 最新的推荐
     * @param pageSize
     * @return
     */
    List<Map<String,Object>> findHomeArticle(Integer pageSize);
}

package cn.jeefast.service;

import cn.jeefast.entity.HjArticle;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 文章 服务类
 * </p>
 *
 * @author zhihang
 * @since 2018-11-21
 */
public interface HjArticleService extends IService<HjArticle> {

    List<HjArticle> findAdArticle(Integer pageIndex, Integer pageSzie);

    List<HjArticle> findLikeArticle(Long articleId);
}

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
 * @since 2018-11-18
 */
public interface HjArticleService extends IService<HjArticle> {

    /**
     * 文章默认最新的推荐，综合
     * @return
     */
    List<Map<String,Object>> findNewArticle(Integer page,Integer pageSize,String categoryCode);

    /**
     * 推荐喜欢,排除当前条，并且点击最高率低于该条的数据
     * @param categoryCode
     * @return
     */
    List<Map<String, Object>> findYourLike(String categoryCode, Long currentArticleId,Integer readOk,Integer pageSize);
}

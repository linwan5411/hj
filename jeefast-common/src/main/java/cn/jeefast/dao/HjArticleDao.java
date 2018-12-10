package cn.jeefast.dao;

import cn.jeefast.entity.HjArticle;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
  * 文章 Mapper 接口
 * </p>
 *
 * @author zhihang
 * @since 2018-11-21
 */
public interface HjArticleDao extends BaseMapper<HjArticle> {
    /**
   * 更新，排除空值
   */
    int updateByHjArticle(HjArticle entity);

    List<Map<String,Object>> findAdArticle(@Param("pageIndex") Integer pageIndex, @Param("pageSize") Integer pageSize);

    List<Map<String,Object>> findLikeArticle(@Param("code")Long code, @Param("pageSize")int pageSize, @Param("articleId")Long  articleId);

    int articleLight(@Param("articleId")Long articleId);
}
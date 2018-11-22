package cn.jeefast.dao;

import cn.jeefast.entity.HjArticle;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    List<HjArticle> findAdArticle(@Param("pageIndex") Integer pageIndex,@Param("pageSize") Integer pageSize);

    List<HjArticle> findLikeArticle(@Param("code")String code, @Param("pageSize")int pageSize, @Param("articleId")Long  articleId);
}
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
 * @since 2018-11-18
 */
public interface HjArticleDao extends BaseMapper<HjArticle> {


    List<Map<String,Object>> findNewArticle(@Param("categoryCode")String categoryCode,
                                            @Param("page") Integer page,
                                            @Param("pageSize")Integer pageSize);

    List<Map<String,Object>> findYourLike(@Param("categoryCode")String categoryCode,
                                    @Param("currentArticleId")Long currentArticleId,
                                    @Param("readOk")Integer readOk,
                                    @Param("pageSize")Integer pageSize);
}
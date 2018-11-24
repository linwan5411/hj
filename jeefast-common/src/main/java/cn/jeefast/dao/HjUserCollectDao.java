package cn.jeefast.dao;

import cn.jeefast.entity.HjUserCollect;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
  * 用户的收藏表 Mapper 接口
 * </p>
 *
 * @author zhihang
 * @since 2018-11-24
 */
public interface HjUserCollectDao extends BaseMapper<HjUserCollect> {

    List<Map<String,Object>> findServer(@Param("userId") Long userId,
                                        @Param("userId") Double lat,
                                        @Param("userId") Double lng,
                                        @Param("pageIndex")Integer pageIndex,
                                        @Param("pageSize")Integer pageSize);

    List<Map<String,Object>> findLand(@Param("userId") Long userId,
                                      @Param("userId") Double lat,
                                      @Param("userId") Double lng,
                                      @Param("pageIndex")Integer pageIndex,
                                      @Param("pageSize")Integer pageSize);
}
package cn.jeefast.dao;

import cn.jeefast.entity.HjHaciendaInfo;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
  * 农场主 Mapper 接口
 * </p>
 *
 * @author zhihang
 * @since 2018-11-22
 */
public interface HjHaciendaInfoDao extends BaseMapper<HjHaciendaInfo> {

    List<Map<String,Object>> findLandMore(@Param("lng") Double lng,
                                            @Param("lat")Double lat,
                                            @Param("areaCode")String areaCode,
                                            @Param("authType")Integer authType,
                                            @Param("userType")Integer userType,
                                            @Param("pageIndex")Integer pageIndex,
                                            @Param("pageSize")Integer pageSize,
                                            @Param("categoryCode")String categoryCode);

    List<Map<String,Object>> findLandByUserId(@Param("userId")Long userId);
}
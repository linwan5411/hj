package cn.jeefast.dao;

import cn.jeefast.entity.HjServerInfo;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
  * 服务商详请 Mapper 接口
 * </p>
 *
 * @author zhihang
 * @since 2018-11-20
 */
public interface HjServerInfoDao extends BaseMapper<HjServerInfo> {

    List<Map<String,Object>> findServerMore(@Param("lng") Double lng,
                                            @Param("lat")Double lat,
                                            @Param("areaCode")String areaCode,
                                            @Param("authType")Integer authType,
                                            @Param("userType")Integer userType,
                                            @Param("pageIndex")Integer pageIndex,
                                            @Param("pageSize")Integer pageSize,
                                            @Param("categoryCode")String categoryCode);
}
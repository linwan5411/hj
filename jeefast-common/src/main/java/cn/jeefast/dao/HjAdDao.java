package cn.jeefast.dao;

import cn.jeefast.entity.HjAd;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
  * 广告 Mapper 接口
 * </p>
 *
 * @author zhihang
 * @since 2018-11-18
 */
public interface HjAdDao extends BaseMapper<HjAd> {

    List<Map<String,Object>> findAdBySite(@Param("siteId") Long siteId);
}
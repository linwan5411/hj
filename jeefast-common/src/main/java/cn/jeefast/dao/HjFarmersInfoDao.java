package cn.jeefast.dao;

import cn.jeefast.entity.HjFarmersInfo;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
  * 农场主认证 Mapper 接口
 * </p>
 *
 * @author zhihang
 * @since 2018-12-13
 */
public interface HjFarmersInfoDao extends BaseMapper<HjFarmersInfo> {
    /**
   * 更新，排除空值
   */
    int updateByHjFarmersInfo(HjFarmersInfo entity);

}
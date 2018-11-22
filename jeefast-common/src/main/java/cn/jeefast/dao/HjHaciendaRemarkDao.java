package cn.jeefast.dao;

import cn.jeefast.entity.HjHaciendaRemark;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
  * 农场的土地介绍相关 Mapper 接口
 * </p>
 *
 * @author zhihang
 * @since 2018-11-22
 */
public interface HjHaciendaRemarkDao extends BaseMapper<HjHaciendaRemark> {
    /**
   * 更新，排除空值
   */
    int updateByHjHaciendaRemark(HjHaciendaRemark entity);

}
package cn.jeefast.dao;

import cn.jeefast.entity.HjOrder;
import cn.jeefast.vo.RecordRps;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
  * 联系表 Mapper 接口
 * </p>
 *
 * @author zhihang
 * @since 2018-11-24
 */
public interface HjOrderDao extends BaseMapper<HjOrder> {
    /**
   * 更新，排除空值
   */
    int updateByHjOrder(HjOrder entity);

    List<RecordRps> orderListByTen(@Param("size") Integer size,
                                   @Param("beforeTime")String beforeTime,
                                   @Param("type")Integer type,
                                   @Param("objectId")Long objectId);
}
package cn.jeefast.dao;

import cn.jeefast.entity.HjArea;
import cn.jeefast.vo.AreaVo;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
  * 地区码表 Mapper 接口
 * </p>
 *
 * @author zhihang
 * @since 2018-11-17
 */
@Repository
public interface HjAreaDao extends BaseMapper<HjArea> {

    List<AreaVo> findAreaByParentId(@Param("areaId") Long areaId);

    HjArea selectByName(@Param("areaCode")String areaCode);
}
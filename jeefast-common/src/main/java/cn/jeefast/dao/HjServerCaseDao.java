package cn.jeefast.dao;

import cn.jeefast.entity.HjServerCase;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
  * 服务商案例 Mapper 接口
 * </p>
 *
 * @author zhihang
 * @since 2018-11-20
 */
public interface HjServerCaseDao extends BaseMapper<HjServerCase> {

    List<HjServerCase> myCaseList(@Param("serverId") Long serverId);
}
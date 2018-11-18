package cn.jeefast.dao;

import cn.jeefast.entity.HjServerCode;
import cn.jeefast.vo.CategoryCode;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
  * 服务类别编码 Mapper 接口
 * </p>
 *
 * @author zhihang
 * @since 2018-11-18
 */
public interface HjServerCodeDao extends BaseMapper<HjServerCode> {

    List<CategoryCode> findByParenId(@Param("parentId") Long parentId);
}
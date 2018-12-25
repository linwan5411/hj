package cn.jeefast.dao;

import cn.jeefast.entity.HjMessage;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
  * 用户的消息通知 Mapper 接口
 * </p>
 *
 * @author zhihang
 * @since 2018-12-25
 */
public interface HjMessageDao extends BaseMapper<HjMessage> {
    /**
   * 更新，排除空值
   */
    int updateByHjMessage(HjMessage entity);

    List<Map<String,Object>> findMyMessage(@Param("userId") Long userId,
                                           @Param("pageIndex")Integer pageIndex,
                                           @Param("pageSize")Integer pageSize);

    int viewMessage(@Param("userId")Long userId, @Param("messageId")Long messageId);
}
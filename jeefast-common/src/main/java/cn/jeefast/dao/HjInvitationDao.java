package cn.jeefast.dao;

import cn.jeefast.entity.HjInvitation;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
  * 帖子 Mapper 接口
 * </p>
 *
 * @author zhihang
 * @since 2018-11-21
 */
public interface HjInvitationDao extends BaseMapper<HjInvitation> {
    /**
   * 更新，排除空值
   */
    int updateByHjInvitation(HjInvitation entity);

    List<HjInvitation> findAdNote(@Param("pageIndex") Integer pageIndex, @Param("pageSize")Integer pageSize);

    int noteOk(@Param("noteId")Long noteId);

    int updateAnswerNum(@Param("noteId")Long invitationId);
}
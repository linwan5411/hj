package cn.jeefast.dao;

import cn.jeefast.entity.HjInvitationList;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
  * 回复列表 Mapper 接口
 * </p>
 *
 * @author zhihang
 * @since 2018-11-21
 */
public interface HjInvitationListDao extends BaseMapper<HjInvitationList> {

    List<HjInvitationList> findNoteInvitation(@Param("noteId") Long noteId,@Param("pageIndex") Integer pageIndex,
                                              @Param("pageSize")Integer pageSize);

    int commentIdOk(@Param("commentId")Long commentId);
}
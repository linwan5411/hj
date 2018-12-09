package cn.jeefast.service;

import cn.jeefast.entity.HjInvitationList;
import cn.jeefast.entity.HjUser;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 回复列表 服务类
 * </p>
 *
 * @author zhihang
 * @since 2018-11-21
 */
public interface HjInvitationListService extends IService<HjInvitationList> {

    List<HjInvitationList> findNoteInvitation(Long noteId, Integer pageIndex, Integer pageSize);

    Integer findListNum(Long noteId);

    void doComment(HjUser user, Long invitationId, String comment);

    void commentIdOk(Long commentId);
}

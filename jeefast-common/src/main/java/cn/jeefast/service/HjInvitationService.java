package cn.jeefast.service;

import cn.jeefast.entity.HjInvitation;
import cn.jeefast.entity.HjUser;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 帖子 服务类
 * </p>
 *
 * @author zhihang
 * @since 2018-11-21
 */
public interface HjInvitationService extends IService<HjInvitation> {

    List<HjInvitation> findAdNote(Integer pageIndex, Integer page);

    HjInvitation findAdNoteInfo(Long noteId);

    void noteOk(Long noteId);

    void submitNote(String imageList, String notComment, String noteType, HjUser user);
}

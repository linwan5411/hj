package cn.jeefast.service.impl;

import cn.jeefast.dao.HjInvitationDao;
import cn.jeefast.entity.HjInvitationList;
import cn.jeefast.dao.HjInvitationListDao;
import cn.jeefast.entity.HjUser;
import cn.jeefast.service.HjInvitationListService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Wrapper;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 回复列表 服务实现类
 * </p>
 *
 * @author zhihang
 * @since 2018-11-21
 */
@Service
public class HjInvitationListServiceImpl extends ServiceImpl<HjInvitationListDao, HjInvitationList> implements HjInvitationListService {

    @Resource
    private HjInvitationListDao hjInvitationListDao;

    @Resource
    private HjInvitationDao hjInvitationDao;

    @Override
    public List<HjInvitationList> findNoteInvitation(Long noteId, Integer pageIndex, Integer pageSize) {
        return hjInvitationListDao.findNoteInvitation(noteId,pageIndex,pageSize);
    }

    @Override
    public Integer findListNum(Long noteId) {
        EntityWrapper wrapper = new EntityWrapper();
        wrapper.where("invitation_id={0}",noteId);
        return hjInvitationListDao.selectCount(wrapper);
    }

    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    @Override
    public void doComment(HjUser user, Long invitationId, String comment) {
        HjInvitationList list = new HjInvitationList();
        list.setInvitationId(invitationId);
        list.setInvitationInfo(comment);
        list.setUserId(user.getUserId());
        list.setUserName(user.getUserName());
        list.setUserHeader(user.getUserPortrait());
        list.setCreateTime(new Date());
        hjInvitationListDao.insert(list);

        //新增
        hjInvitationDao.updateAnswerNum(invitationId);
    }

    @Override
    public void commentIdOk(Long commentId) {
       try {
           hjInvitationListDao.commentIdOk(commentId);
       }catch (Exception e){

       }
    }

    @Async
    @Override
    public void doReadNum(Long noteId) {
        try {
            hjInvitationListDao.doReadNum(noteId);
        }catch (Exception e){

        }
    }
}

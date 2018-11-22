package cn.jeefast.service.impl;

import cn.jeefast.entity.HjInvitation;
import cn.jeefast.dao.HjInvitationDao;
import cn.jeefast.entity.HjInvitationList;
import cn.jeefast.service.HjInvitationService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 帖子 服务实现类
 * </p>
 *
 * @author zhihang
 * @since 2018-11-21
 */
@Service
public class HjInvitationServiceImpl extends ServiceImpl<HjInvitationDao, HjInvitation> implements HjInvitationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HjInvitationServiceImpl.class);

    @Resource
    private HjInvitationDao hjInvitationDao;

    @Override
    public List<HjInvitation> findAdNote(Integer pageIndex, Integer pageSzie) {
        return hjInvitationDao.findAdNote(pageIndex,pageSzie);
    }

    @Override
    public HjInvitation findAdNoteInfo(Long noteId) {
        HjInvitation t = new HjInvitation();
        t.setInvitationId(noteId);
        return hjInvitationDao.selectOne(t);
    }

    @Override
    public void noteOk(Long noteId) {
        try {
            hjInvitationDao.noteOk(noteId);
        }catch (Exception e){
            LOGGER.error("noteOk exp:{}",e);
        }
    }
}

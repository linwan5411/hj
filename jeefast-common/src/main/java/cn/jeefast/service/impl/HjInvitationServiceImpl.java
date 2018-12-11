package cn.jeefast.service.impl;

import cn.jeefast.common.enums.ResultEnum;
import cn.jeefast.common.exception.BusinessException;
import cn.jeefast.common.utils.KeyGeneratorUtils;
import cn.jeefast.entity.HjInvitation;
import cn.jeefast.dao.HjInvitationDao;
import cn.jeefast.entity.HjInvitationList;
import cn.jeefast.entity.HjServerCode;
import cn.jeefast.entity.HjUser;
import cn.jeefast.service.HjInvitationService;
import cn.jeefast.service.HjServerCodeService;
import cn.jeefast.vo.CategoryCode;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
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

    @Resource
    private HjServerCodeService hjServerCodeService;

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

    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    @Override
    public void submitNote(String imageList, String notComment, String noteType, HjUser user) {
        HjInvitation invitation = new HjInvitation();
        invitation.setInvitationId(KeyGeneratorUtils.getLongValue());
        invitation.setCreateTime(new Date());
       if(StringUtils.isNotBlank(imageList)){
           if(Arrays.asList(imageList.split(",")).size() > 9){
               throw new BusinessException("最多上传9张图片", ResultEnum.REQ_MAX_IMAGE_EXP.getCode());
           }
           invitation.setInvitationImages(imageList);
       }
        invitation.setUserId(user.getUserId());
        invitation.setUserHeader(user.getUserPortrait());
        invitation.setUserName(user.getUserName());
        invitation.setInvitationInfo(notComment);
        HjServerCode c = hjServerCodeService.findByCode(noteType);
        if(c != null){
            invitation.setArticleCategoryCode(c.getCategoryCode());
            invitation.setArticleCategory(c.getServerCategory());
        }
        hjInvitationDao.insert(invitation);
    }
}

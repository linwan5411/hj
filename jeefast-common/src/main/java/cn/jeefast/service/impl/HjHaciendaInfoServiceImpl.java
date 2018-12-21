package cn.jeefast.service.impl;

import cn.jeefast.common.enums.ResultEnum;
import cn.jeefast.common.exception.BusinessException;
import cn.jeefast.common.utils.KeyGeneratorUtils;
import cn.jeefast.common.utils.LocationUtils;
import cn.jeefast.config.RedisUtils;
import cn.jeefast.config.redis.Cacheable;
import cn.jeefast.dao.HjFarmersInfoDao;
import cn.jeefast.dao.HjHaciendaRemarkDao;
import cn.jeefast.dao.HjUserDao;
import cn.jeefast.entity.*;
import cn.jeefast.dao.HjHaciendaInfoDao;
import cn.jeefast.service.HjAreaService;
import cn.jeefast.service.HjArticleService;
import cn.jeefast.service.HjHaciendaInfoService;
import cn.jeefast.service.HjServerCodeService;
import cn.jeefast.vo.CategoryCode;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * <p>
 * 农场主 服务实现类
 * </p>
 *
 * @author zhihang
 * @since 2018-11-22
 */
@Service
public class HjHaciendaInfoServiceImpl extends ServiceImpl<HjHaciendaInfoDao, HjHaciendaInfo> implements HjHaciendaInfoService {

    @Resource
    private HjHaciendaInfoDao hjHaciendaInfoDao;

    @Resource
    private HjHaciendaRemarkDao hjHaciendaRemarkDao;

    @Resource
    private HjAreaService hjAreaService;

    @Resource
    private HjServerCodeService hjServerCodeService;

    @Resource
    private HjUserDao hjUserDao;

    @Resource
    private HjFarmersInfoDao hjFarmersInfoDao;

    @Resource
    private RedisUtils redisUtils;


    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    @Override
    public Long landApprove(HjHaciendaInfo info, List<HjHaciendaRemark> list, Long userId) {
        HjArea area = hjAreaService.findByAreaId(info.getDistrict());
        if(area != null){
            info.setAreaCode(area.getRelationCode());
        }else{
            throw new BusinessException("地址参数不正确", ResultEnum.REQ_PARAM_EXP.getCode());
        }
        if(info.getHaciendaType() == null || info.getHaciendaType() > 2 || info.getHaciendaType() < 0){
            throw new BusinessException("类型参数不正确", ResultEnum.REQ_PARAM_EXP.getCode());
        }
        //土地性质
        if(StringUtils.isNotBlank(info.getServerCategory())){
            List<CategoryCode> codes = hjServerCodeService.findCodeList(info.getServerCategory());
            if(codes != null && codes.size() > 0){
                StringBuffer buffer = new StringBuffer();
                codes.forEach( e ->{
                    buffer.append(e.getServerCategory()).append(",");
                });
                info.setHaciendaLand(buffer.toString().substring(0,buffer.toString().length() - 1));
            }
        }

        //土地性质
        if(StringUtils.isNotBlank(info.getNeedServer())){
            List<CategoryCode> codes = hjServerCodeService.findCodeList(info.getNeedServer());
            if(codes != null && codes.size() > 0){
                StringBuffer buffer = new StringBuffer();
                codes.forEach( e ->{
                    buffer.append(e.getServerCategory()).append(",");
                });
                info.setNeedServerName(buffer.toString().substring(0,buffer.toString().length() - 1));
            }
        }

        HjFarmersInfo d = new HjFarmersInfo();d.setUserId(userId);
        d = hjFarmersInfoDao.selectOne(d);
        if(d != null){

        }

        if(info.getHaciendaId() != null){
            //update
            info.setUpdateTime(new Date());
            //删除，然后在添加
            Map<String,Object> map = new HashMap<>();map.put("hacienda_id",info.getHaciendaId());
            hjHaciendaRemarkDao.deleteByMap(map);

            if(list != null && list.size() > 0){
                for(HjHaciendaRemark re : list){
                    re.setHaciendaId(info.getHaciendaId());
                    re.setCreateTime(new Date());
                    if(StringUtils.isBlank(info.getHaciendaImage()) && StringUtils.isNoneBlank(re.getHaciendaImage())){
                        info.setHaciendaImage(re.getHaciendaImage());
                    }
                    hjHaciendaRemarkDao.insert(re);
                }
            }
            EntityWrapper entityWrapper = new EntityWrapper();
            entityWrapper.where("hacienda_id={0}",info.getHaciendaId());
            hjHaciendaInfoDao.update(info,entityWrapper);

        }else{
            info.setHaciendaId(KeyGeneratorUtils.getLongValue());
            info.setUserId(userId);
            info.setCreateTime(new Date());
            if(list != null && list.size() > 0){
                for(HjHaciendaRemark re : list){
                    re.setHaciendaId(info.getHaciendaId());
                    re.setCreateTime(new Date());
                    if(StringUtils.isBlank(info.getHaciendaImage()) && StringUtils.isNoneBlank(re.getHaciendaImage())){
                        info.setHaciendaImage(re.getHaciendaImage());
                    }
                    hjHaciendaRemarkDao.insert(re);
                }
            }

            int row = hjHaciendaInfoDao.insert(info);
            if(row <= 0){
                throw new BusinessException("认证失败", ResultEnum.SER_AUTH_EXP.getCode());
            }
        }
        return info.getHaciendaId();
    }

    @Override
    public List<Map<String, Object>> findLand(Long areaId, Double lat, Double lng, int homeSize) {
        String hkey = "land_notify";

        String ser_key = areaId.toString();
        Object obj = redisUtils.get(hkey,ser_key);
        if(obj != null){
            return (List<Map<String, Object>>) obj;
        }else{
            //服务商通知
            List<Map<String,Object>> list = findLandMore(lng,lat,
                    areaId,null,null,0, homeSize,null);
            if(list != null && list.size() > 0){
                redisUtils.put(hkey,ser_key,list);
            }else{
                list = findLandMore(lng,lat,
                        null,null,null,0, homeSize,null);
                if(list != null && list.size() > 0){
                    redisUtils.put(hkey,ser_key,list);
                }
            }
            return list;
        }
    }

    @Override
    public List<Map<String, Object>> findLandMore(Double lng, Double lat, Long areaId, Integer authType, Integer userType, Integer pageIndex, Integer pageSize, String categoryCode) {
        String areaCode = null;
        if(areaId != null){
            HjArea area = hjAreaService.findByAreaId(areaId);
            if(area != null){
                areaCode = area.getRelationCode();
            }
        }
        if(StringUtils.isBlank(categoryCode)){
            categoryCode = null;
        }
        if(authType == null || authType == 0){
            authType = null;
        }
        if(userType == null || userType == 0){
            userType = null;
        }
        return hjHaciendaInfoDao.findLandMore(lng,lat,areaCode,authType,userType,pageIndex,pageSize,categoryCode);
    }

    @Cacheable(key = "wanhe_HjHaciendaInfo",fieldKey = "#haciendaId",expireTime = 86000)
    @Override
    public HjHaciendaInfo findLandDetail(Long haciendaId) {
        HjHaciendaInfo hjServerInfo = new HjHaciendaInfo();hjServerInfo.setHaciendaId(haciendaId);
        hjServerInfo = hjHaciendaInfoDao.selectOne(hjServerInfo);
        if(hjServerInfo == null){
            return null;
        }
        if(StringUtils.isNotBlank(hjServerInfo.getServerCategory())){
            hjServerInfo.setCategoryList(Arrays.asList(hjServerInfo.getServerCategory().split(",")));
        }
        EntityWrapper entityWrapper = new EntityWrapper();entityWrapper.where("hacienda_id={0}",hjServerInfo.getHaciendaId());
        List<HjHaciendaRemark> remaks = hjHaciendaRemarkDao.selectList(entityWrapper);
        hjServerInfo.setRemarks(remaks);

        //查询用户的头像等
        HjUser user = new HjUser();user.setUserId(hjServerInfo.getUserId());
        user = hjUserDao.selectOne(user);
        if(user != null){
            hjServerInfo.setHeaderImage(user.getUserPortrait());
        }

        //农场主的名称
        HjFarmersInfo hjFarmersInfo = new HjFarmersInfo();hjFarmersInfo.setUserId(hjServerInfo.getUserId());
        hjFarmersInfo = hjFarmersInfoDao.selectOne(hjFarmersInfo);
        if(hjFarmersInfo != null){
            hjServerInfo.setFramersNickName(hjFarmersInfo.getFarmersName());
        }
        return hjServerInfo;
    }

    @Override
    public List<HjHaciendaInfo> findLandByUserId(Long userId) {
        List<HjHaciendaInfo> l = hjHaciendaInfoDao.findLandByUserId(userId);
        return l;
    }
}

package cn.jeefast.service.impl;

import cn.jeefast.common.enums.ResultEnum;
import cn.jeefast.common.exception.BusinessException;
import cn.jeefast.common.key.KeyGenerator;
import cn.jeefast.common.utils.KeyGeneratorUtils;
import cn.jeefast.config.RedisUtils;
import cn.jeefast.dao.HjServerCaseDao;
import cn.jeefast.dao.HjServerRemakDao;
import cn.jeefast.entity.HjArea;
import cn.jeefast.entity.HjServerCase;
import cn.jeefast.entity.HjServerInfo;
import cn.jeefast.dao.HjServerInfoDao;
import cn.jeefast.entity.HjServerRemak;
import cn.jeefast.service.HjAreaService;
import cn.jeefast.service.HjServerCodeService;
import cn.jeefast.service.HjServerInfoService;
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
 * 服务商详请 服务实现类
 * </p>
 *
 * @author zhihang
 * @since 2018-11-20
 */
@Service
public class HjServerInfoServiceImpl extends ServiceImpl<HjServerInfoDao, HjServerInfo> implements HjServerInfoService {

    @Resource
    private HjAreaService hjAreaService;

    @Resource
    private HjServerInfoDao hjServerInfoDao;

    @Resource
    private HjServerRemakDao hjServerRemakDao;

    @Resource
    private HjServerCaseDao hjServerCaseDao;

    @Resource
    private HjServerCodeService hjServerCodeService;

    @Resource
    private RedisUtils redisUtils;

    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    @Override
    public Long userApprove(HjServerInfo info, List<HjServerRemak> list, Long userId) {
        HjArea area = hjAreaService.findByAreaId(info.getDistrict());
        if(area != null){
            info.setAreaCode(area.getRelationCode());
        }else{
            throw new BusinessException("地址参数不正确", ResultEnum.REQ_PARAM_EXP.getCode());
        }
        if(info.getServerType() == null || info.getServerType() > 2 || info.getServerType() < 1){
            throw new BusinessException("类型参数不正确", ResultEnum.REQ_PARAM_EXP.getCode());
        }
        if(info.getServerType() == 2 && StringUtils.isBlank(info.getServerRegImage())){
            throw new BusinessException("请上传营业执照", ResultEnum.REQ_PARAM_EXP.getCode());
        }

        //服务的类型
        if(StringUtils.isNotBlank(info.getServerCodes())){
            List<CategoryCode> codes = hjServerCodeService.findCodeList(info.getServerCodes());
            if(codes != null && codes.size() > 0){
                StringBuffer buffer = new StringBuffer();
                codes.forEach( e ->{
                    buffer.append(e.getServerCategory()).append(",");
                });
                info.setServerCategory(buffer.toString().substring(0,buffer.toString().length() - 1));
            }
        }

        if(info.getServerId() != null){
            //update
            info.setUpdateTime(new Date());
            //删除，然后在添加
            Map<String,Object> map = new HashMap<>();map.put("server_id",info.getServerId());
            hjServerRemakDao.deleteByMap(map);

            if(list != null && list.size() > 0){
                for(HjServerRemak re : list){
                    re.setServerId(info.getServerId());
                    re.setCreateTime(new Date());
                    if(StringUtils.isBlank(info.getCompanyImage()) && StringUtils.isNotBlank(re.getServerImage())){
                        info.setCompanyImage(re.getServerImage());
                    }
                    hjServerRemakDao.insert(re);
                }
            }

            //进行修改 。。。。。。 hjServerCaseRemarkDao.up();
            EntityWrapper entityWrapper = new EntityWrapper();
            entityWrapper.where("server_id={0}",info.getServerId());
            hjServerInfoDao.update(info,entityWrapper);

        }else{
            info.setServerId(KeyGeneratorUtils.getLongValue());
            info.setUserId(userId);
            info.setCreateTime(new Date());
            if(list != null && list.size() > 0){
                for(HjServerRemak re : list){
                    re.setServerId(info.getServerId());
                    re.setCreateTime(new Date());
                    if(StringUtils.isBlank(info.getCompanyImage()) && StringUtils.isNotBlank(re.getServerImage())){
                        info.setCompanyImage(re.getServerImage());
                    }
                    hjServerRemakDao.insert(re);
                }
            }

            int row = hjServerInfoDao.insert(info);
            if(row <= 0){
                throw new BusinessException("认证失败", ResultEnum.SER_AUTH_EXP.getCode());
            }
        }
        return info.getServerId();
    }

    @Override
    public List<Map<String, Object>> findServerMore(Double lng, Double lat,
                                                    Long areaId, Integer authType, Integer userType, Integer pageIndex, Integer pageSize, String categoryCode) {
        String areaCode = null;
        if(areaId != null){
            HjArea area = hjAreaService.findByAreaId(areaId);
            if(area != null){
                areaCode = area.getRelationCode();
            }
        }

        return hjServerInfoDao.findServerMore(lng,lat,areaCode,authType,userType,pageIndex,pageSize,categoryCode);
    }

    @Override
    public List<Map<String, Object>> findServerAndLand(Long areaId, Double lat, Double lng,int size) {
        String hkey = "server_notify";

        String ser_key = areaId.toString();
        Object obj = redisUtils.get(hkey,ser_key);
        if(obj != null){
            return (List<Map<String, Object>>) obj;
        }else{
            //服务商通知
            List<Map<String,Object>> list = findServerMore(lng,lat,
                    areaId,null,null,0, size,null);
            if(list != null && list.size() > 0){
                redisUtils.put(hkey,ser_key,list);
            }else{
                list =  findServerMore(lng,lat,
                        null,null,null,0, size,null);
                if(list != null && list.size() > 0){
                    redisUtils.put(hkey,ser_key,list);
                }
            }
            return list;
        }
    }

    @Override
    public HjServerInfo findServerDetail(Long serverId) {
        HjServerInfo hjServerInfo = new HjServerInfo();hjServerInfo.setServerId(serverId);
        hjServerInfo = hjServerInfoDao.selectOne(hjServerInfo);
        if(hjServerInfo == null){
            return null;
        }
        if(StringUtils.isNotBlank(hjServerInfo.getServerCategory())){
            hjServerInfo.setCategoryList(Arrays.asList(hjServerInfo.getServerCategory().split(",")));
        }
        EntityWrapper entityWrapper = new EntityWrapper();entityWrapper.where("server_id={0}",hjServerInfo.getServerId());
        List<HjServerRemak> remaks = hjServerRemakDao.selectList(entityWrapper);
        hjServerInfo.setRemaks(remaks);

        List<HjServerCase> caseList = hjServerCaseDao.selectList(entityWrapper);
        hjServerInfo.setCaseList(caseList);
        return hjServerInfo;
    }
}

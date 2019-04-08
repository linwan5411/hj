package cn.jeefast;

import cn.jeefast.common.utils.*;
import cn.jeefast.config.RedisUtils;
import cn.jeefast.dao.*;
import cn.jeefast.entity.*;
import cn.jeefast.service.HjAreaService;
import cn.jeefast.service.HjUserService;
import cn.jeefast.utls.PoiUtils;
import cn.jeefast.vo.AreaVo;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.*;

@RunWith(SpringRunner.class)
@ComponentScan(value = "cn.jeefast")
@SpringBootTest
public class DynamicDataSourceTest {

    @Resource
    private HjUserDao hjUserDao;

    @Resource
    private HjServerInfoDao hjServerInfoDao;

    @Resource
    private HjHaciendaInfoDao hjHaciendaInfoDao;

    @Resource
    private HjAreaDao hjAreaDao;

    @Resource
    private HjServerCodeDao hjServerCodeDao;

    @Resource
    private HjFarmersInfoDao hjFarmersInfoDao;

    @Resource
    private HjServerRemakDao hjServerRemakDao;

    @Resource
    private HjHaciendaRemarkDao hjHaciendaRemarkDao;


    @Resource
    private RedisUtils redisUtils;



    @Test
    public void redisTest(){
        redisUtils.delete("wh_myZoneData","101987157914");
    }

    /**
     * 添加服务商的导入处理
     */
    @Test
    public void testJoinServer() {
        List<HjServerInfo> s = PoiUtils.serverList();
        for (HjServerInfo h : s) {
            try {
                HjUser user = new HjUser();
                user.setUserId(KeyGeneratorUtils.getLongValue());
                if (StringUtils.isNotBlank(h.getLinkPhone())) {
                    user.setUserMobile(h.getLinkPhone());
                } else {
                    user.setUserMobile(KeyGeneratorUtils.getLongValue() + "");
                }
                user.setLoginSalt("123456");
                user.setLoginPwd(PwdUtils.createPwd("123456", user.getLoginSalt()));
                user.setUserName("新农人" + RandomUtils.randomNumber(4));
                user.setAuthType(0);
                user.setUserType(2);
                user.setCreateTime(new Date());

                hjUserDao.insert(user);

                h.setUserId(user.getUserId());
                h.setServerId(KeyGeneratorUtils.getLongValue());
                h.setCreateTime(new Date());
                h.setProvice(2236L);
                h.setCity(2237L);
                h.setDistrict(2255L);
                h.setAreaCode("2236,2237,2255");
                h.setLatitude(29.356311D);
                h.setLongitude(105.927001D);

                HjServerRemak hjServerRemak = new HjServerRemak();
                hjServerRemak.setServerId(h.getServerId());
                hjServerRemak.setServerInfo(h.getCompanyScope());
                hjServerRemak.setCreateTime(new Date());
                hjServerRemakDao.insert(hjServerRemak);


                h.setCompanyScope(null);
                hjServerInfoDao.insert(h);
            } catch (Exception e) {
                System.out.println(JsonUtils.Bean2Json(h));
            }
        }

    }


    @Test
    public void testLand() {
        int xxx = 0;
        List<HjHaciendaInfo> infos = PoiUtils.landList();
        for (HjHaciendaInfo h : infos) {
            try {
                HjUser user = new HjUser();
                if (StringUtils.isNotBlank(h.getLinkPhone())) {
                    user.setUserMobile(h.getLinkPhone());
                } else {
                    user.setUserMobile(KeyGeneratorUtils.getLongValue() + "");
                }
                user.setLoginSalt("123456");
                user.setLoginPwd(PwdUtils.createPwd("123456", user.getLoginSalt()));
                user.setUserName("新农人" + RandomUtils.randomNumber(4));
                user.setAuthType(0);
                user.setUserType(1);
                user.setDataStatus(1);
                user.setCreateTime(new Date());
                user.setUserId(KeyGeneratorUtils.getLongValue());
                //System.out.println(JsonUtils.Bean2Json(user));
                hjUserDao.insert(user);


                HjFarmersInfo fs = new HjFarmersInfo();
                fs.setUserId(user.getUserId());
                fs.setFarmersId(KeyGeneratorUtils.getLongValue());
                if (StringUtils.isNotBlank(h.getHaciendaName())) {
                    fs.setFarmersName(h.getHaciendaName());
                    fs.setFarmersType(2);
                } else {
                    fs.setFarmersName(user.getUserName());
                    fs.setFarmersType(1);
                }
                fs.setLinkName(h.getLinkName());
                fs.setLinkPhone(h.getLinkPhone());
                fs.setCreateTime(new Date());
                fs.setAuthStatus(0);
                fs.setAuthStatus(0);
                fs.setFarmersType(1);
                fs.setFarmersRemark(h.getHaciendaRemark());
                System.out.println(JsonUtils.Bean2Json(fs));


                h.setFramersNickName(fs.getFarmersName());
                h.setFarmersId(fs.getFarmersId());
                h.setUserId(user.getUserId());
                HjArea hjArea = hjAreaDao.selectByName(h.getAreaCode());
                if(hjArea != null){
                    String re = hjArea.getRelationCode();
                    h.setProvice(Long.valueOf(re.split(",")[0]));
                    h.setCity(Long.valueOf(re.split(",")[1]));
                    h.setDistrict(Long.valueOf(re.split(",")[2]));
                    String c = hjArea.getCenter();
                    h.setLatitude(Double.valueOf(c.split(",")[1]));
                    h.setLongitude(Double.valueOf(c.split(",")[0]));
                    fs.setLongitude(h.getLongitude());
                    fs.setLatitude(h.getLatitude());
                }

                h.setHaciendaId(KeyGeneratorUtils.getLongValue());
                h.setCreateTime(new Date());
                hjFarmersInfoDao.insert(fs);
                hjHaciendaInfoDao.insert(h);
                //System.out.println(JsonUtils.Bean2Json(h));
                //System.out.println(JsonUtils.Bean2Json(fs));

                HjHaciendaRemark remark = new HjHaciendaRemark();
                remark.setHaciendaInfo(h.getHaciendaRemark());
                remark.setHaciendaId(h.getHaciendaId());
                remark.setCreateTime(new Date());
                hjHaciendaRemarkDao.insert(remark);
                //System.out.println(JsonUtils.Bean2Json(remark));

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(JsonUtils.Bean2Json(h));
            }
            //break;

        }

    }

}

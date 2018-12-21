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

    /*@Test
    public void testJoinServer() {
        List<HjServerInfo> s = PoiUtils.serverList();
        for (HjServerInfo h : s) {
            try {
                HjUser user = new HjUser();
                user.setUserId(KeyGeneratorUtils.getLongValue());
                if (StringUtils.isNotBlank(h.getLinkPhone())) {
                    String[] arr = h.getLinkPhone().split(",");
                    user.setUserMobile(arr[0]);
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
                h.setProvice(1681L);
                h.setCity(1712L);
                h.setDistrict(1725L);
                h.setAreaCode("1681,1712,1725");
                h.setLatitude(30.42594D);
                h.setLongitude(111.76053D);
                //hjServerInfoDao.insert(h);
            } catch (Exception e) {
                System.out.println(JsonUtils.Bean2Json(h));
            }
        }

    }


    @Test
    public void testLand(){
        int xxx = 0;
        List<HjHaciendaInfo> infos = PoiUtils.landList();
        for(HjHaciendaInfo h : infos){
            try {
                HjUser user = new HjUser();
                user.setUserId(KeyGeneratorUtils.getLongValue());
                if (StringUtils.isNotBlank(h.getLinkPhone())) {
                    String[] arr = h.getLinkPhone().split(",");
                    user.setUserMobile(arr[0]);
                } else {
                    user.setUserMobile(KeyGeneratorUtils.getLongValue() + "");
                }
                user.setLoginSalt("123456");
                user.setLoginPwd(PwdUtils.createPwd("123456", user.getLoginSalt()));
                user.setUserName("新农人" + RandomUtils.randomNumber(4));
                user.setAuthType(0);
                user.setUserType(1);
                user.setCreateTime(new Date());

                hjUserDao.insert(user);


                HjFarmersInfo fs = new HjFarmersInfo();
                fs.setUserId(user.getUserId());
                fs.setFarmersId(KeyGeneratorUtils.getLongValue());
                if(StringUtils.isNotBlank(h.getHaciendaName())) {
                    fs.setFarmersName(h.getHaciendaName());
                    fs.setFarmersType(2);
                }else{
                    fs.setFarmersName(user.getUserName());
                    fs.setFarmersType(1);
                }
                fs.setCreateTime(new Date());
                hjFarmersInfoDao.insert(fs);

                h.setFramersNickName(fs.getFarmersName());
                h.setFarmersId(fs.getFarmersId());
                h.setUserId(user.getUserId());

                if("土地平整".equals(h.getNeedServerName())){
                    h.setNeedServer("12");
                }else if("测土施肥".equals(h.getNeedServerName())){
                    h.setNeedServer("13");
                }else if("育苗播种".equals(h.getNeedServerName())){
                    h.setNeedServer("14");
                }else if("专业植保".equals(h.getNeedServerName())){
                    h.setNeedServer("15");
                }else if("农机收割".equals(h.getNeedServerName())){
                    h.setNeedServer("16");
                }else if("土地托管".equals(h.getNeedServerName())){
                    h.setNeedServer("17");
                }else if("订单收购".equals(h.getNeedServerName())){
                    h.setNeedServer("18");
                }

                if("耕地".equals(h.getHaciendaLand())){
                    h.setServerCategory("21");
                }else if("园地".equals(h.getHaciendaLand())){
                    h.setServerCategory("22");
                }else if("林地".equals(h.getHaciendaLand())){
                    h.setServerCategory("23");
                }else if("水域".equals(h.getHaciendaLand())){
                    h.setServerCategory("24");
                }else if("牧草地".equals(h.getHaciendaLand())){
                    h.setServerCategory("25");
                }

                if("四川".equals(h.getDetailAddr())){
                    h.setProvice(2277L);
                    h.setLongitude(Double.valueOf(104.075809));
                    h.setLatitude(Double.valueOf(30.651239));
                    h.setAreaCode(2277+"");
                }else{
                    h.setProvice(2236L);
                    h.setLongitude(Double.valueOf(106.551643));
                    h.setLatitude(Double.valueOf(29.562849));
                    h.setAreaCode(2236+"");
                }
                h.setHaciendaId(KeyGeneratorUtils.getLongValue());
                h.setCreateTime(new Date());
                hjHaciendaInfoDao.insert(h);
                //System.out.println(JsonUtils.Bean2Json(h));
            }catch (Exception e){
                e.printStackTrace();
                System.out.println(JsonUtils.Bean2Json(h));
            }
            xxx++;
        }

    }*/

}

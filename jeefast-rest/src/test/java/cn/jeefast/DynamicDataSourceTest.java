package cn.jeefast;

import cn.jeefast.common.utils.JsonUtils;
import cn.jeefast.config.RedisUtils;
import cn.jeefast.entity.HjArea;
import cn.jeefast.entity.HjUser;
import cn.jeefast.service.HjAreaService;
import cn.jeefast.service.HjUserService;
import cn.jeefast.vo.AreaVo;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@ComponentScan(value = "cn.jeefast")
@SpringBootTest
public class DynamicDataSourceTest {

    @Resource
    private HjAreaService hjAreaService;

    @Resource
    private RedisUtils redisUtils;

    @Resource
    private HjUserService hjUserService;

    /**
     * 测试Wrapper
     */
    //@Test
    public void testUser(){
        //hjUserService.createNewUser("13368466998","123456",0);
        HjUser user = new HjUser();
        user.setUserId(1L);user.setUserName("kkkkk");
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.where("id={0}",1L);
        hjUserService.update(user,entityWrapper);
    }

    //@Test
    public void test1() throws Exception {
        List<AreaVo> list = hjAreaService.findAreaByParentId(-1L);

        System.out.println(JsonUtils.Bean2Json(list));
    }

    //@Test
    public void testRedis(){
        String key = "AreaPid-1";
        List<AreaVo> list = (List<AreaVo>) redisUtils.getValue(key);
        System.out.println(JsonUtils.Bean2Json(list));
    }

}

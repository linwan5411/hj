package cn.jeefast;

import cn.jeefast.entity.HjArea;
import cn.jeefast.service.HjAreaService;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import cn.jeefast.system.entity.SysUser;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@ComponentScan(value = "cn.jeefast")
@SpringBootTest
public class DynamicDataSourceTest {
    @Autowired
    private DataSourceTestService dataSourceTestService;

    @Test
    public void test(){
        //数据源1
    	SysUser user = dataSourceTestService.queryObject(1L);
        System.out.println(ToStringBuilder.reflectionToString(user));

        //数据源2
        SysUser user2 = dataSourceTestService.queryObject2(1L);
        System.out.println(ToStringBuilder.reflectionToString(user2));

        //数据源1
        SysUser user3 = dataSourceTestService.queryObject(1L);
        System.out.println(ToStringBuilder.reflectionToString(user3));
    }

    @Resource
    private HjAreaService hjAreaService;

    @Test
    public void test1() throws Exception {
        HjArea a = new HjArea();a.setAreaId(111111L);a.setAreaName("test");
        hjAreaService.insert(a);

        a.setAreaName("xxxxx");
        hjAreaService.updateById(a);
    }


}

package cn.jeefast.config.redis;

import org.springframework.context.annotation.ComponentScan;

import java.lang.annotation.*;

/**
 *redis 开启注解
 * @author lc
 * @date 2018/8/31
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@ComponentScan(
        basePackages = {"cn.jeefast.config"}
)
public @interface EnableRedis {
}
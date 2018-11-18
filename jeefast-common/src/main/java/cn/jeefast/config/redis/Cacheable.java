package cn.jeefast.config.redis;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by lyj on 2017/3/31.
 * 定义一个缓存的注解,一般利用redis
 *
 *  key != null && fieldKey != null ==== > hash
 *  key != null && fieldKey == null ==== > string
 *
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Cacheable {

    /**
     * 缓存的目标key
     * @return
     */
    String key();

    /**
     * 缓存的参数，支持EpLS表达式来操作
     *
     *  String key = "'selectSecretByKey'+#partnerId"; selectSecretByKey字符串，后面是表达式动态参数值
     *
     * @return
     */
    String fieldKey() default "";

    /**
     * 失效时间,以秒计算 TimeUnit.SECONDS,默认为8600秒，-1表示不过期
     * @return
     */
    long expireTime() default 8600;
}

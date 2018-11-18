package cn.jeefast.config.redis;

import cn.jeefast.config.RedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;


/**
 * Created by lyj on 2017/3/31.
 * 定义一个缓存的拦截切面,使用的hash值存储,存储关系为LinkdHashMAP
 */

@Component
@Aspect
public class CacheAspect {

    private   static Logger logger = LoggerFactory.getLogger(CacheAspect.class);

    @Resource
    private RedisUtils redisUtils;

    @Value("${redis.cache.switch:true}")
    private boolean bu_cache;

    /**
     * 定义缓存逻辑
     */

    @Around("@annotation(cn.jeefast.config.redis.Cacheable)")
    public Object cache(ProceedingJoinPoint pjp) {

        Object result = null;
        //判断是否开启缓存,这个可以自定义的进行设计，判断redis是否开启
        Method method = getMethod(pjp);

        if(!bu_cache || method == null){
            proceed(pjp);
        }

        Cacheable cacheable = method.getAnnotation(cn.jeefast.config.redis.Cacheable.class);
        String key = parseKey(cacheable.key(), method, pjp.getArgs());
        long expireTime = cacheable.expireTime();
        if(cacheable == null || StringUtils.isBlank(key)) {
            proceed(pjp);
        }
        String fieldKey = parseKey(cacheable.fieldKey(), method, pjp.getArgs());

        //获取方法的返回类型,让缓存可以返回正确的类型
        //Class returnType = ((MethodSignature) pjp.getSignature()).getReturnType();

        //使用redis 的hash进行存取，易于管理,获取缓存
        try {
            if(StringUtils.isBlank(fieldKey)){
                result = redisUtils.getValue(key);
            }else {
                result = redisUtils.get(key, fieldKey);
            }
        }catch (Exception e){
            logger.error("CacheAspect.redis连接失败:{}",e);
        }

        //表示未从缓存中取
        if (result == null) {
            try {
                result = pjp.proceed();
                //如果缓存中数据为空，那么得到将是数据库中的数据，然后再次保存到缓存中的机制来做的.
                if(result != null) {
                    if(StringUtils.isBlank(fieldKey)){
                        if(expireTime > 0){
                            redisUtils.setValue(key, result, expireTime, TimeUnit.SECONDS);
                        }else{
                            redisUtils.setValue(key, result);
                        }
                    }else{
                        if(expireTime > 0){
                            redisUtils.put(key, fieldKey, result, expireTime, TimeUnit.SECONDS);
                        }else{
                            redisUtils.put(key, fieldKey, result);
                        }
                    }
                }
            } catch (Throwable e) {
                logger.error("CacheAspect.cache:从数据库查询数据放入缓存中异常:{}",e);
            }
        }
        return result;
    }



    /**
     * 回调原有方法
     * @param pjp
     * @return
     */

    private Object proceed(ProceedingJoinPoint pjp){
        try {
            return pjp.proceed();
        } catch (Throwable e) {
            logger.error("缓存调用原有的方法出现异常:{}",e);
            return null;
        }
    }

    /**
     *  获取被拦截方法对象
     *  MethodSignature.getMethod() 获取的是顶层接口或者父类的方法对象
     *  而缓存的注解在实现类的方法上
     *  所以应该使用反射获取当前对象的方法对象
     */

    private Method getMethod(ProceedingJoinPoint pjp){
        try {
            //获取参数的类型
            Object [] args=pjp.getArgs();
            Class [] argTypes=new Class[pjp.getArgs().length];
            for(int i=0;i<args.length;i++){
                argTypes[i]=args[i].getClass();
            }
            Method method=null;
            try {
                method=pjp.getTarget().getClass().getMethod(pjp.getSignature().getName(),argTypes);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (SecurityException e) {
                e.printStackTrace();
            }
            return method;
        }catch (Exception e){
            logger.error("CacheAspect.getMethod:反射获取注解缓存参数异常:{}",e);
        }
        return null;
    }


    /**
     * 获取缓存的key
     * key 定义在注解上，支持SPEL表达式
     * @param key
     * @param method
     * @param args
     * @return
     */

    private String parseKey(String key,Method method,Object [] args){
        try {
            if(StringUtils.isBlank(key)){
                return key;
            }
            //获取被拦截方法参数名列表(使用Spring支持类库)
            LocalVariableTableParameterNameDiscoverer u =
                    new LocalVariableTableParameterNameDiscoverer();
            String [] paraNameArr=u.getParameterNames(method);

            //使用SPEL进行key的解析
            ExpressionParser parser = new SpelExpressionParser();
            //SPEL上下文
            StandardEvaluationContext context = new StandardEvaluationContext();
            //把方法参数放入SPEL上下文中
            for(int i=0;i<paraNameArr.length;i++){
                context.setVariable(paraNameArr[i], args[i]);
            }
            return parser.parseExpression(key).getValue(context,String.class);
        }catch (Exception e){
            logger.error("CacheAspect.parseKey:解析缓存参数异常:{}",e);
        }
        return null;
    }
}


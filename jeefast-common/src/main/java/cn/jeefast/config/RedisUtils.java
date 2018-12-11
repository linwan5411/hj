package cn.jeefast.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Redis工具类
 *
 * @author theodo
 * @email 36780272@qq.com
 * @date 2017-10-17 21:12
 */
@Component
public class RedisUtils<K,HK,V> {

    private final Logger logger = LoggerFactory.getLogger(RedisUtils.class);

    @Autowired
    private RedisTemplate redisTemplate;
    private HashOperations<K,HK,V> hashOperations;
    private ZSetOperations<K,V> zSetOperations;
    private SetOperations<K,V> setOperations;
    private ValueOperations<K,V> valueOperations;
    private ListOperations<K,V> listOperations;

    @PostConstruct
    public void init() {
        hashOperations = redisTemplate.opsForHash ();
        zSetOperations = redisTemplate.opsForZSet();
        setOperations = redisTemplate.opsForSet();
        valueOperations = redisTemplate.opsForValue();
        listOperations = redisTemplate.opsForList();
    }
    public void put(K key, HK hashKey, V value) {
        try {
            hashOperations.put(key, hashKey, value);
        }catch (Exception e){
            logger.error("redis put exp");
        }
    }

    public void put(K key, HK hashKey, V value,long expireTime, TimeUnit timeUnit){
        try {
            hashOperations.put(key, hashKey, value);
            if(expireTime > 0) {
                redisTemplate.expire(key, expireTime, timeUnit);
            }

        }catch (Exception e){
            logger.error("redis put exp");
        }
    }

    public V get(K key,HK hashKey) {
        try {
            return hashOperations.get(key,hashKey);
        }catch (Exception e){
            return null;
        }
    }

    public void setValue(K key,V value,long expireTime, TimeUnit timeUnit){
        try {
            valueOperations.set(key,value);
            if(expireTime > 0) {
                redisTemplate.expire(key, expireTime, timeUnit);
            }
        }catch (Exception e){
            logger.error("redis setValue exp");
        }
    }

    public void delete(K key,HK hashKey) {
        try {
            hashOperations.delete(key,hashKey);
        }catch (Exception e){
            logger.error("redis delete exp");
        }
    }

    public Long sAdd(K key,V value) {
        try {
            return setOperations.add(key,value);
        }catch (Exception e){
            return 0L;
        }
    }

    /**
     * 返回如果为1，那么说明添加成功，0：表示添加失败
     * @param key
     * @param value
     * @param expireTime
     * @param timeUnit
     * @return
     */
    public Long sAdd(K key,V value,long expireTime, TimeUnit timeUnit) {
        try {
            Long index = setOperations.add(key,value);
            if(expireTime > 0) {
                redisTemplate.expire(key, expireTime, timeUnit);
            }
            return index;
        }catch (Exception e){
            return 0L;
        }
    }

    public void deleteSAdd(K key,V value){
        try {
            setOperations.remove(key, value);
        }catch (Exception e){
            logger.error("redis deleteSAdd exp ");
        }
    }

    public Set<V> distinctRandomMembers(K key, long count) {
        return setOperations.distinctRandomMembers(key,count);
    }

    public boolean zAdd(K key,V value,long score) {
        try {
            return zSetOperations.add(key,value,score);
        }catch (Exception e){
            return false;
        }
    }

    public Long remove(K key,V value) {
        try {
            return zSetOperations.remove(key,value);
        }catch (Exception e){
            return 0L;
        }
    }

    public Long size(K key) {
        try {
            return zSetOperations.size(key);
        }catch (Exception e){
            return 0L;
        }
    }

    public boolean expire(K key,long expireTime, TimeUnit timeUnit) {
        try {
            return redisTemplate.expire(key, expireTime, timeUnit);
        }catch (Exception e){
            return false;
        }
    }

    public Boolean expireAt(K key, Date date) {
        try {
            return redisTemplate.expireAt(key, date);
        } catch (Exception e) {
            logger.error("redis expireAt 异常",e);
        }
        return null;
    }

    public Long getExpire(K key,TimeUnit timeUnit) {
        try {
            return redisTemplate.getExpire(key,timeUnit);
        } catch (Exception e) {
            logger.error("redis getExpire 异常",e);
        }
        return null;
    }

    public Set<ZSetOperations.TypedTuple<V>> getRangeWithScores(K key, long start, long end) {
        return zSetOperations.reverseRangeWithScores(key,start,end);
    }

    public Cursor<ZSetOperations.TypedTuple<V>> scan(K key, String pattern) {
        ScanOptions scanOptions = new ScanOptions.ScanOptionsBuilder().match(pattern).build();
        return zSetOperations.scan(key,scanOptions);
    }

    public Long getSetSize(K key){
        try {
            return getSetOperations().size(key);
        }catch (Exception e){
            logger.error("redis getSetSize 异常",e);
        }
        return null;
    }

    public Long addSet(K key,V... v){
        try {
            return getSetOperations().add(key,v);
        }catch (Exception e){
            logger.error("redis addSet 异常",e);
        }
        return null;
    }

    public void setValue(K key,V v){
        try {
            getValueOperations().set(key,v);
        }catch (Exception e){
            logger.error("redis setValue 异常",e);
        }
    }

    public V getValue(K key){
        try {
            return getValueOperations().get(key);
        }catch (Exception e){
            logger.error("redis getValue 异常",e);
        }
        return null;
    }

    public Boolean setIfAbsent(K key,V v){
        try {
            return getValueOperations().setIfAbsent(key,v);
        }catch (Exception e){
            logger.error("redis setIfAbsent 异常",e);
        }
        return null;
    }

    public void deleteKey(K key){
        try {
            redisTemplate.delete(key);
        }catch (Exception e){
            logger.error("redis deleteKey 异常",e);
        }
    }

    public Double getzSetScore(K key,Object o){
        try {
            return getzSetOperations().score(key,o);
        }catch (Exception e){
            logger.error("redis getzSetScore 异常",e);
        }
        return null;
    }

    public Long getzSetReverseRank(K key, Object o){
        try {
            return getzSetOperations().reverseRank(key,o);
        }catch (Exception e){
            logger.error("redis getzSetReverseRank 异常",e);
        }
        return null;
    }

    public Double incrementScore(K key,V v,double v1){
        try {
            return getzSetOperations().incrementScore(key,v,v1);
        }catch (Exception e){
            logger.error("redis incrementScore 异常",e);
        }
        return null;
    }

    public Boolean setZAdd(K key,V v,double v1 ){
        try {
            return getzSetOperations().add(key,v,v1);
        }catch (Exception e){
            logger.error("redis setZAdd 异常",e);
        }
        return null;
    }

    public Long setZAdd(K key, Set<ZSetOperations.TypedTuple<V>> set){
        try {
            return getzSetOperations().add(key,set);
        }catch (Exception e){
            logger.error("redis setZAdd 异常",e);
        }
        return null;
    }

    public Long rightPushAll(K key, Collection<V> values){
        try {
            return getListOperations().rightPushAll(key,values);
        }catch (Exception e){
            logger.error("redis rightPushAll 异常",e);
        }
        return null;
    }

    public V leftPop(K key){
        try {
            return getListOperations().leftPop(key);
        }catch (Exception e){
            logger.error("redis leftPop 异常",e);
        }
        return null;
    }

    public Long listSize(K key){
        try {
            return getListOperations().size(key);
        }catch (Exception e){
            logger.error("redis listSize 异常",e);
        }
        return null;
    }

    public HashOperations<K, HK, V> getHashOperations() {
        return hashOperations;
    }

    public ZSetOperations<K, V> getzSetOperations() {
        return zSetOperations;
    }

    public SetOperations<K, V> getSetOperations() {
        return setOperations;
    }

    public ValueOperations<K, V> getValueOperations() {
        return valueOperations;
    }

    public ListOperations<K, V> getListOperations() {
        return listOperations;
    }

    public RedisTemplate getRedisTemplate() {
        return redisTemplate;
    }
}

package com.swing.sky.center.framework.datasource.redis;

import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * spring redis 工具类
 *
 * @author swing
 **/
@SuppressWarnings(value = {"unchecked", "rawtypes"})
@Component
public class RedisUtils {
    /**
     * redis模板
     */
    @Resource
    public RedisTemplate redisTemplate;


    /**
     * 存入基本的对象，Integer、String、实体类等
     *
     * @param key   缓存的键值
     * @param value 缓存的值
     * @return 缓存的对象
     */
    public <T> ValueOperations<String, T> setObject(String key, T value) {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        operation.set(key, value);
        return operation;
    }


    /**
     * 存入基本的对象，Integer、String、实体类等 (有过期时间）
     *
     * @param key      缓存的键值
     * @param value    缓存的值
     * @param timeout  时间
     * @param timeUnit 时间单位
     * @return 缓存的对象
     */
    public <T> ValueOperations<String, T> setObject(String key, T value, Integer timeout, TimeUnit timeUnit) {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        operation.set(key, value, timeout, timeUnit);
        return operation;
    }

    /**
     * 读取缓存的基本对象。
     *
     * @param key 缓存键值
     * @return 缓存键值对应的数据
     */
    public <T> T getObject(String key) {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        return operation.get(key);
    }


    /**
     * 存入List对象
     *
     * @param key      缓存的键值
     * @param dataList 待缓存的List数据
     * @return 缓存的对象
     */
    public <T> ListOperations<String, T> setList(String key, List<T> dataList) {
        ListOperations listOperation = redisTemplate.opsForList();
        if (null != dataList) {
            int size = dataList.size();
            for (T t : dataList) {
                listOperation.leftPush(key, t);
            }
        }
        return listOperation;
    }

    /**
     * 读取list对象
     *
     * @param key 缓存的键值
     * @return 缓存键值对应的数据
     */
    public <T> List<T> getList(String key) {
        List<T> dataList = new ArrayList<T>();
        ListOperations<String, T> listOperation = redisTemplate.opsForList();
        Long size = listOperation.size(key);
        if (size != null) {
            for (int i = 0; i < size; i++) {
                dataList.add(listOperation.index(key, i));
            }
        }
        return dataList;
    }

    /**
     * 存入Set
     *
     * @param key     缓存键值
     * @param dataSet 缓存的数据
     * @return 缓存数据的对象
     */
    public <T> BoundSetOperations<String, T> setSet(String key, Set<T> dataSet) {
        BoundSetOperations<String, T> setOperation = redisTemplate.boundSetOps(key);
        for (T t : dataSet) {
            setOperation.add(t);
        }
        return setOperation;
    }

    /**
     * 读取set
     *
     * @param key 键
     * @return 值
     */
    public <T> Set<T> getSet(String key) {
        Set<T> dataSet = new HashSet<T>();
        BoundSetOperations<String, T> operation = redisTemplate.boundSetOps(key);
        dataSet = operation.members();
        return dataSet;
    }

    /**
     * 存入Map
     *
     * @param key     键
     * @param dataMap 数据
     * @return 结果
     */
    public <T> HashOperations<String, String, T> setMap(String key, Map<String, T> dataMap) {
        HashOperations hashOperations = redisTemplate.opsForHash();
        if (null != dataMap) {
            for (Map.Entry<String, T> entry : dataMap.entrySet()) {
                hashOperations.put(key, entry.getKey(), entry.getValue());
            }
        }
        return hashOperations;
    }

    /**
     * 读取Map
     *
     * @param key 键
     * @return 结果
     */
    public <T> Map<String, T> getMap(String key) {
        return (Map<String, T>) redisTemplate.opsForHash().entries(key);
    }


    /**
     * 读取的基本对象列表
     *
     * @param pattern 字符串前缀
     * @return 对象列表
     */
    public Collection<String> listKeys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    /**
     * 删除单个记录
     *
     * @param key 键
     */
    public void deleteObjects(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 删除集合对象
     *
     * @param collection 集合
     */
    public void deleteObjects(Collection collection) {
        redisTemplate.delete(collection);
    }
}

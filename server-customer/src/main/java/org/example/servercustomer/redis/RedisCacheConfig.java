package org.example.servercustomer.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
public class RedisCacheConfig {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private ObjectMapper objectMapper;

    /**
     * 获取缓存数据，如果缓存不存在则从数据源获取并缓存
     *
     * @param key         缓存key
     * @param clazz       数据类型
     * @param provider    数据提供者
     * @param expireTime  过期时间（秒）
     * @param <T>         数据类型
     * @return 数据对象
     */
    public <T> T getCachedData(String key, Class<T> clazz, DataProvider<T> provider, long expireTime) {
        try {
            // 尝试从缓存中获取数据
            Object cachedValue = redisTemplate.opsForValue().get(key);
            if (cachedValue != null) {
                if (cachedValue instanceof String) {
                    // 如果是JSON字符串，则反序列化
                    String json = (String) cachedValue;
                    if (StringUtils.hasText(json)) {
                        return objectMapper.readValue(json, clazz);
                    }
                } else {
                    // 如果已经是对象类型，直接返回
                    return clazz.cast(cachedValue);
                }
            }

            // 如果缓存中没有数据，则从数据源中获取数据
            T data = provider.getData();
            if (data != null) {
                // 将数据存储到缓存中，并设置过期时间
                String jsonValue = objectMapper.writeValueAsString(data);
                redisTemplate.opsForValue().set(key, jsonValue, expireTime, TimeUnit.SECONDS);
            }
            return data;
        } catch (JsonProcessingException e) {
            // 如果JSON序列化失败，尝试直接存储对象
            try {
                Object cachedValue = redisTemplate.opsForValue().get(key);
                if (cachedValue != null) {
                    return clazz.cast(cachedValue);
                }

                T data = provider.getData();
                if (data != null) {
                    redisTemplate.opsForValue().set(key, data, expireTime, TimeUnit.SECONDS);
                }
                return data;
            } catch (Exception ex) {
                throw new RuntimeException("Cache operation failed", ex);
            }
        } catch (Exception e) {
            throw new RuntimeException("Cache operation failed", e);
        }
    }

    /**
     * 清除缓存
     *
     * @param key 缓存key
     * @return 是否成功
     */
    public boolean deleteCache(String key) {
        return Boolean.TRUE.equals(redisTemplate.delete(key));
    }

    /**
     * 更新缓存
     *
     * @param key        缓存key
     * @param value      新的值
     * @param expireTime 过期时间（秒）
     * @param <T>        数据类型
     */
    public <T> void updateCache(String key, T value, long expireTime) {
        try {
            if (value != null) {
                String jsonValue = objectMapper.writeValueAsString(value);
                redisTemplate.opsForValue().set(key, jsonValue, expireTime, TimeUnit.SECONDS);
            }
        } catch (JsonProcessingException e) {
            // 如果JSON序列化失败，直接存储对象
            redisTemplate.opsForValue().set(key, value, expireTime, TimeUnit.SECONDS);
        }
    }

    /**
     * 数据提供者接口
     *
     * @param <T> 数据类型
     */
    @FunctionalInterface
    public interface DataProvider<T> {
        T getData();
    }

}

package com.fei.springboothigh.config.redis;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @Author: xiaoshijiu
 * @Date: 2019/6/1
 * @Description: 自定义Redis配置类，顺带开启缓存
 */
@Configuration
@EnableCaching
public class RedisConfig {

    /**
     * 自定义RedisTemplates，实现对象按json形式序列化
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(
            RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        // 给模板设置json序列化器，key和value分别设置
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(redisValueSerializer());
        return template;
    }

    /**
     * 自定义Redis缓存管理器
     */
    @Bean
    public RedisCacheManager redisCacheManager(RedisConnectionFactory redisConnectionFactory,
            GenericJackson2JsonRedisSerializer redisValueSerializer) {
        return RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(RedisCacheConfiguration.defaultCacheConfig()
                        .serializeValuesWith(RedisSerializationContext.SerializationPair
                                .fromSerializer(redisValueSerializer)))
                .build();
    }

    /**
     * 这里使用GenericJackson2JsonRedisSerializer 进行对象序列化成json操作
     */
    @Bean
    public GenericJackson2JsonRedisSerializer redisValueSerializer() {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, new ToStringSerializer());
        simpleModule.addSerializer(long.class, new ToStringSerializer());
        objectMapper.registerModule(simpleModule);
        // 开启@class（方便反序列化时候，能够根据class类型识别回来）
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL,
                JsonTypeInfo.As.PROPERTY);
        GenericJackson2JsonRedisSerializer redisValueSerializer = new GenericJackson2JsonRedisSerializer(
                objectMapper);
        return redisValueSerializer;
    }
}

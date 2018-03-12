package com.github.wolfclub.oneblog.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.lang.reflect.Method;

/**
 * @author liuchan
 * @date 11:00 2018/2/7
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport{

    @Bean
    public CacheManager cacheManager(@Qualifier("myRedisTemplate") RedisTemplate<String, String> redisTemplate) {
        RedisCacheManager rcm = new RedisCacheManager(redisTemplate);
        return rcm;
    }

    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return new KeyGenerator(){
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                for (Object param : params) {
                    sb.append(param.toString());
                }
                return sb.toString();
            }
        };
    }

    @Bean("myRedisTemplate")
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory){
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(factory);
        template.setEnableTransactionSupport(true);
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }

}

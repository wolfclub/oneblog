package com.github.wolfclub.oneblog.test;

import com.github.wolfclub.oneblog.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RunWith(value = SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
public class TestRedis extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    @Qualifier("myRedisTemplate")
    private RedisTemplate template;

    @Test
    @Rollback(value = false)
    public void testInsertObject() {
        Map<String, String> map =new HashMap<>();
        map.put("aaa", "aaa");
        map.put("bbb", "bbb");
        ValueOperations<Serializable, Object> operations = template.opsForValue();
        operations.set("key0013", map);
    }

    @Test
    @Rollback(value = false)
    public void testInsertString() {
        ValueOperations<Serializable, Object> operations = template.opsForValue();
        String s = "123";
        operations.set("key0013", s);
    }

    @Test
    @Rollback(value = false)
    public void testInsertWithTime() {
        ValueOperations<Serializable, Object> operations = template.opsForValue();
        operations.set("key002", "value002");
        template.expire("key002", 1000*20, TimeUnit.MILLISECONDS);
    }

    @Test
    public void testExists() {
        template.hasKey("key001");
    }

    @Test
    public void testGet() {
        ValueOperations<Serializable, Object> operations = template.opsForValue();
        System.out.println("key0013: " + operations.get("key0013"));
    }

}

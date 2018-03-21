package com.github.wolfclub.oneblog.test;

import com.github.wolfclub.oneblog.Application;
import com.github.wolfclub.oneblog.commons.query.AggregateType;
import com.github.wolfclub.oneblog.mapper.UserMapper;
import com.github.wolfclub.oneblog.po.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

@RunWith(value = SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
public class TestGenerator extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    @Rollback(value = false)
    public void testInsert() {
        User user = new User();
        user.setCode("lc111");
        user.setName("liuchan");
        user.setPassword("123456");
        user.setCreatorCode("lc1");
        user.setCreatorName("liuchan1");
        user.setCreateTime(new Date());
        user.setLastModifierCode("lc2");
        user.setLastModifierName("liuchan2");
        user.setLastModifyTime(new Date());
        user.setVersionTime(new Date());
        user.setVersion(0L);
        userMapper.insert(user);

        System.out.println(user.getId());
    }

    @Test
    public void testSelect() {

        userMapper.selectByExample(null);
//        User user2 = userMapper.selectByPrimaryKey(1L);
//        System.out.println(user);
    }

    @Test
    @Rollback(value = false)
    public void testUpdateVersion() {

        User user = userMapper.selectByPrimaryKey(1L);
//        User user2 = userMapper.selectByPrimaryKey(1L);
        user.setPhone("5");
        int i = userMapper.updateByPrimaryKey(user);
        System.out.println("phone:"+user.getPhone()+ "  version:"+ user.getVersion() +"  result:"+i);

        user.setPhone("6");
        i = userMapper.updateByPrimaryKey(user);
        System.out.println("phone:"+user.getPhone()+ "  version:"+ user.getVersion() +"  result:"+i);
    }


    @Test
    public void testSelect1() {
        Object m = userMapper.selectAggregationByExample(new Example(User.class), AggregateType.count, "id", "name");
        System.out.println(m);
    }


}

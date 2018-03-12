package com.github.wolfclub.oneblog.test;

import com.github.wolfclub.oneblog.Application;
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

@RunWith(value = SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
public class TestVersionUpdate extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private UserMapper userMapper;


    @Test
    @Rollback(value = false)
    public void testUpdateVersion() {

        User user = userMapper.selectByPrimaryKey(5L);
//        User user = new User();
//        user.setId(5L);
//        user.setCode("lc2");
//        user.setName("liuchan");
//        user.setPassword("123456");
        user.setPhone("1223");
        user.setVersion(1L);
        int i = userMapper.updateByPrimaryKeySelective(user);
        System.out.println("id:"+user.getId()+ "  version:"+ user.getVersion() +"  result:"+i);

    }
}

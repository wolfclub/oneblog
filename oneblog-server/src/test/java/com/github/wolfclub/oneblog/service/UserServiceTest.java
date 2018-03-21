package com.github.wolfclub.oneblog.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.wolfclub.oneblog.Application;
import com.github.wolfclub.oneblog.commons.entity.OperateContext;
import com.github.wolfclub.oneblog.commons.entity.Operator;
import com.github.wolfclub.oneblog.commons.query.AggregateQueryFilter;
import com.github.wolfclub.oneblog.commons.query.AggregateType;
import com.github.wolfclub.oneblog.exception.ServiceException;
import com.github.wolfclub.oneblog.exception.VersionConflictException;
import com.github.wolfclub.oneblog.po.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author liuchan
 * @date 11:52 2018/2/7
 */
@RunWith(value = SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
public class UserServiceTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	UserService userService;

	ObjectMapper objectMapper;

	@Before
	public void setUp() throws Exception {
		objectMapper = new ObjectMapper();
		objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
	}

	@After
	public void tearDown() throws Exception {
	}


	@Test
//	@Rollback(value = false)
	public void save() throws ServiceException, VersionConflictException, JsonProcessingException {

		System.out.println("\nUserServiceTest:save 开始测试 >>>");

		User user = new User();
		user.setCode("test01");
		user.setName("测试01");
		user.setPassword("123456");
		user.setPhone("12334");

		OperateContext operateContext = new OperateContext()
						.setOperator(new Operator("testUser01", "测试用户01"))
						.setTime(new Date());

		System.out.println("--------  新增测试 --------");
		System.out.println("新增前数据 >> " + objectMapper.writeValueAsString(user));
		int result = userService.save(user, operateContext);
		System.out.println("新增结果 >>  result=" + result);
		System.out.println("新增后数据 >> " + objectMapper.writeValueAsString(user));

		System.out.println("\n--------  修改测试 --------");
		operateContext.getOperator().setOperatorName("testUser02").setOperatorName("测试用户02");
		user = userService.get(user.getId());
		System.out.println("修改前数据 >> " + objectMapper.writeValueAsString(user));
		result = userService.save(user, operateContext);
		System.out.println("修改结果 >>  result=" + result);
		System.out.println("修改后数据 >> " + objectMapper.writeValueAsString(user));

		System.out.println("<<<  UserServiceTest:save 测试结束\n");
	}

	@Test
	@Rollback(value = false)
	public void saveSelective() throws ServiceException, VersionConflictException, JsonProcessingException {
		System.out.println("\nUserServiceTest:saveSelective 开始测试 >>>");

		User user = new User();
		user.setCode("test01");
		user.setName("测试01");
		user.setPassword("123456");
		user.setPhone("12334");

		OperateContext operateContext = new OperateContext()
						.setOperator(new Operator("testUser01", "测试用户01"))
						.setTime(new Date());

		System.out.println("--------  新增测试 --------");
		System.out.println("新增前数据 >> " + objectMapper.writeValueAsString(user));
		int result = userService.saveSelective(user, operateContext);
		System.out.println("新增结果 >>  result=" + result);
		System.out.println("新增后数据 >> " + objectMapper.writeValueAsString(user));

		System.out.println("\n--------  修改测试 --------");
		operateContext.getOperator().setOperatorName("testUser02").setOperatorName("测试用户02");
		user = userService.get(user.getId());
		System.out.println("修改前数据 >> " + objectMapper.writeValueAsString(user));
		result = userService.saveSelective(user, operateContext);
		System.out.println("修改结果 >>  result=" + result);
		System.out.println("修改后数据 >> " + objectMapper.writeValueAsString(user));

		System.out.println("<<<  UserServiceTest:saveSelective 测试结束\n");
	}

	@Test
	public void delete() {
	}

	@Test
	public void get() throws JsonProcessingException {
		System.out.println("\nUserServiceTest:get 开始测试 >>>");

		User user = userService.get(21L);
		System.out.println("查询数据 >> " + objectMapper.writeValueAsString(user));

		System.out.println("<<<  UserServiceTest:get 测试结束\n");
	}

	@Test
	public void select() {
//		System.out.println(applicationContext.getBean(LevelIdGenerator.class).getRule());

		try {
			Object list = userService.selectAggregationByExample(new Example(User.class), new User(), new AggregateQueryFilter().setAggregateType(AggregateType.count).setPropName("id").setGroupBy("name"));
			System.out.println(list);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}


}
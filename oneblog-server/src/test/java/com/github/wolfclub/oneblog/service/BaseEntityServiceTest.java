package com.github.wolfclub.oneblog.service;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
//import org.springframework.test.context.junit4.SpringRunner;
//
///**
// * @author liuchan
// * @date 16:13 2018/2/7
// */
//@RunWith(value = SpringRunner.class)
//@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@EnableAutoConfiguration
//public abstract class BaseEntityServiceTest<T> extends AbstractTransactionalJUnit4SpringContextTests {
//
//	public abstract MyMapper<T> getMapper();
//
//	@Before
//	public void setUp() throws Exception {
//		T t = new T();
//	}
//
//	@After
//	public void tearDown() throws Exception {
//	}
//
//	@Test
//	public void save() {
//	}
//
//	@Test
//	public void saveSelective() {
//	}
//
//	@Test
//	public void delete() {
//	}
//
//	@Test
//	public void get() {
//	}
//
//	@Test
//	public void select() {
//	}
//}
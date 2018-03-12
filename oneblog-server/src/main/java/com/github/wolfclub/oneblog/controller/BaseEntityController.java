package com.github.wolfclub.oneblog.controller;

import com.github.pagehelper.PageInfo;
import com.github.wolfclub.oneblog.common.jpa.PBaseEntity;
import com.github.wolfclub.oneblog.commons.entity.OperateContext;
import com.github.wolfclub.oneblog.commons.entity.Operator;
import com.github.wolfclub.oneblog.exception.ServiceException;
import com.github.wolfclub.oneblog.exception.VersionConflictException;
import com.github.wolfclub.oneblog.service.BaseEntityService;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

/**
 * @author liuchan
 * @date 11:46 2018/2/27
 */
public abstract class BaseEntityController<T extends PBaseEntity> {

	/**
	 * 获取实体对应服务
	 * @return
	 */
	protected abstract BaseEntityService<T> getBaseEntityService();

	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	@ResponseBody
	public T get(@PathVariable String id){

		System.out.println("get 请求");
		T t = getBaseEntityService().get(Long.valueOf(id));
		return t;
	}

	@RequestMapping(value="/save", method = RequestMethod.POST)
	@ResponseBody
	public Object save(@RequestBody T entity) throws ServiceException, VersionConflictException {

		System.out.println("save 请求");
		int i = getBaseEntityService().save(entity, getOperateContext());
		return entity.getId();
	}


	@RequestMapping(value="/query")
	@ResponseBody
	public PageInfo<T> query(@RequestBody() T entity){

		System.out.println("query 请求");
		return getBaseEntityService().selectByExample(new Example(entity.getClass()), entity);
	}

	protected OperateContext getOperateContext(){
		OperateContext operateContext = new OperateContext()
						.setOperator(new Operator("testUser01", "测试用户01"))
						.setTime(new Date());
		return operateContext;
	}

}

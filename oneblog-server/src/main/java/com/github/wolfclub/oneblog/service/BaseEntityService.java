package com.github.wolfclub.oneblog.service;

import com.github.pagehelper.PageInfo;
import com.github.wolfclub.oneblog.common.jpa.PBaseEntity;
import com.github.wolfclub.oneblog.commons.entity.OperateContext;
import com.github.wolfclub.oneblog.commons.query.AggregateQueryFilter;
import com.github.wolfclub.oneblog.exception.ServiceException;
import com.github.wolfclub.oneblog.exception.VersionConflictException;
import tk.mybatis.mapper.entity.Example;

/**
 * @author liuchan
 * @date 11:00 2018/2/7
 */
public interface BaseEntityService<T extends PBaseEntity> {

	/**
	 * 保存（新增和修改）
	 *
	 * @param t       entity
	 * @param operCtx
	 * @return
	 * @throws ServiceException
	 * @throws VersionConflictException
	 */
	int save(T t, OperateContext operCtx) throws ServiceException, VersionConflictException;

	/**
	 * 保存非空属性（新增和修改）
	 *
	 * @param t       entity
	 * @param operCtx
	 * @return
	 * @throws ServiceException
	 * @throws VersionConflictException
	 */
	int saveSelective(T t, OperateContext operCtx) throws ServiceException, VersionConflictException;

	/**
	 * 删除
	 *
	 * @param t       entity
	 * @param operCtx
	 * @return
	 * @throws ServiceException
	 * @throws VersionConflictException
	 */
	int delete(T t, OperateContext operCtx) throws ServiceException, VersionConflictException;

	/**
	 * 根据id获取对象
	 *
	 * @param id
	 * @return
	 */
	T get(Object id);

	/**
	 * 查询集合
	 * @param entity 为null查询全部
	 * @return
	 */
	PageInfo<T> select(T entity);

	/**
	 * 查询集合
	 * @param example
	 * @param entity 为null不分页， filter内参数不解析
	 * @return
	 */
	PageInfo<T> selectByExample(Example example, T entity);

	/**
	 * 聚合查询
	 * @param example
	 * @param entity filter内参数不解析
	 * @return
	 */
	Object selectAggregationByExample(Example example, T entity, AggregateQueryFilter aggregateFilter) throws ServiceException;

}

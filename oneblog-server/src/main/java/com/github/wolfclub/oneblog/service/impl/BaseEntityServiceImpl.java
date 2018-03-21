package com.github.wolfclub.oneblog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.wolfclub.oneblog.common.jpa.PBaseEntity;
import com.github.wolfclub.oneblog.commons.entity.HasVersion;
import com.github.wolfclub.oneblog.commons.entity.OperateContext;
import com.github.wolfclub.oneblog.commons.query.AggregateQueryFilter;
import com.github.wolfclub.oneblog.commons.util.Assert;
import com.github.wolfclub.oneblog.exception.ServiceException;
import com.github.wolfclub.oneblog.exception.VersionConflictException;
import com.github.wolfclub.oneblog.mymapper.MyMapper;
import com.github.wolfclub.oneblog.service.BaseEntityService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.entity.EntityTable;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.mapperhelper.EntityHelper;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author liuchan
 * @date 11:00 2018/2/7
 */
public abstract class BaseEntityServiceImpl<T extends PBaseEntity> implements BaseEntityService<T>, ApplicationContextAware {

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int save(T entity, OperateContext operCtx) throws ServiceException, VersionConflictException {
		Assert.assertArgumentNotNull(entity, "entity");
		vertifyToSave(entity);

		entity.setVersionTime(operCtx.getTime());
		updateLastModifyInfo(entity, operCtx);
		if (entity.getId() == null || entity.getId() == 0) {
			updateCreateInfo(entity, operCtx);
			entity.setVersion(HasVersion.START_VERSION);
			return getEntityMapper().insert(entity);
		} else {
			int flag = getEntityMapper().updateByPrimaryKey(entity);
			if (flag != 1) {
				saveGet(entity.getId(), entity.getVersion());
			}
			return flag;
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int saveSelective(T entity, OperateContext operCtx) throws ServiceException, VersionConflictException {
		Assert.assertArgumentNotNull(entity, "entity");
		vertifyToSave(entity);

		entity.setVersionTime(operCtx.getTime());
		updateLastModifyInfo(entity, operCtx);
		if (entity.getId() == null || entity.getId() == 0) {
			updateCreateInfo(entity, operCtx);
			return getEntityMapper().insertSelective(entity);
		} else {
			int flag = getEntityMapper().updateByPrimaryKeySelective(entity);
			if (flag != 1) {
				saveGet(entity.getId(), entity.getVersion());
			}
			return flag;
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int delete(T entity, OperateContext operCtx) throws ServiceException, VersionConflictException {
		Assert.assertArgumentNotNull(entity, "entity");
		int flag = getEntityMapper().deleteByPrimaryKey(entity.getId());
		if (flag != 1) {
			saveGet(entity.getId(), entity.getVersion());
		}
		return flag;
	}

	@Override
	public T get(Object id) {
		Assert.assertArgumentNotNull(id, "id");
		return getEntityMapper().selectByPrimaryKey(id);
	}

	@Override
	public PageInfo<T> select(T entity) {
		if (entity != null) {
			String orderBy = entity.getSortString(",");
			PageHelper.startPage(entity.getPageNum(), entity.getPageSize(), orderBy);
			return new PageInfo<T>(getEntityMapper().select(entity));
		}
		return new PageInfo<T>(getEntityMapper().selectAll());
	}

	@Override
	public PageInfo<T> selectByExample(Example example, T entity) {
		Assert.assertArgumentNotNull(example, "example");
		if (entity != null) {
			String orderBy = entity.getSortString(",");
			PageHelper.startPage(entity.getPageNum(), entity.getPageSize(), orderBy);
			example.createCriteria().andEqualTo(entity);
		}
		return new PageInfo<T>(getEntityMapper().selectByExample(example));
	}

	@Override
	public List<Map<String, Object>> selectAggregationByExample(Example example, T entity, AggregateQueryFilter aggregateFilter) throws ServiceException {
		Assert.assertArgumentNotNull(example, "example");
		Assert.assertArgumentNotNull(entity, "entity");
		Assert.assertArgumentNotNull(aggregateFilter, "aggregateFilter");
		Assert.assertArgumentNotNull(aggregateFilter.getAggregateType(), "aggregateFilter.aggregateType");
		Assert.assertArgumentNotNull(aggregateFilter.getPropName(), "aggregateFilter.propName");

		EntityTable table = EntityHelper.getEntityTable(entity.getClass());
		Map<String, EntityColumn> columnMap = table.getPropertyMap();

		EntityColumn column = columnMap.get(aggregateFilter.getPropName());
		if (column == null) {
			throw new ServiceException(entity.getClass().getName() + "不存在属性" + aggregateFilter.getPropName());
		}

		if(entity != null){
			example.createCriteria().andEqualTo(entity);
		}
		return getEntityMapper().selectAggregationByExample(example, aggregateFilter.getAggregateType(), column.getColumn(), aggregateFilter.getGroupBy());
	}

	/**
	 * 获取对应版本的数据
	 *
	 * @param id
	 * @param version
	 * @return
	 * @throws ServiceException
	 * @throws VersionConflictException
	 */
	protected T saveGet(Object id, long version) throws ServiceException, VersionConflictException {
		Assert.assertArgumentNotNull(id, "id");

		T perz = getEntityMapper().selectByPrimaryKey(id);
		if (perz == null) {
			throw new ServiceException("指定的{0}不存在。", getEntityCaption(perz));
		} else if (perz.getVersion() != version) {
			throw new VersionConflictException("指定的{0}已被修改，请刷新页面重试。", getEntityCaption(perz));
		}
		return perz;
	}

	protected void vertifyToSave(T entity) throws ServiceException {
	}

	protected void updateCreateInfo(T entity, OperateContext operCtx) {
		entity.setCreateTime(operCtx.getTime() == null ? new Date() : operCtx.getTime());
		if (operCtx.getOperator() != null) {
			entity.setCreatorCode(operCtx.getOperator().getOperatorCode())
							.setCreatorName(operCtx.getOperator().getOperatorName());
		}
	}

	protected void updateLastModifyInfo(T entity, OperateContext operCtx) {
		entity.setLastModifyTime(operCtx.getTime() == null ? new Date() : operCtx.getTime());
		if (operCtx.getOperator() != null) {
			entity.setLastModifierCode(operCtx.getOperator().getOperatorCode())
							.setLastModifierName(operCtx.getOperator().getOperatorName());
		}
	}


	/**
	 * 获取对应mapper
	 *
	 * @return
	 */
	protected abstract MyMapper<T> getEntityMapper();

	/**
	 * 获取对应模块名称
	 *
	 * @param entity
	 * @return
	 */
	protected abstract String getEntityCaption(T entity);


	protected ApplicationContext appCtx;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.appCtx = applicationContext;
	}

	public ApplicationContext getAppCtx() {
		return appCtx;
	}

}

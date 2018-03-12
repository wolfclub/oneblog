package com.github.wolfclub.oneblog.service.impl;

import com.github.wolfclub.oneblog.common.jpa.PBaseEntity;
import com.github.wolfclub.oneblog.commons.entity.OperateContext;
import com.github.wolfclub.oneblog.commons.util.Assert;
import com.github.wolfclub.oneblog.service.BaseExtIdsEntityService;

import java.util.List;

/**
 * @author liuchan
 * @date 11:00 2018/2/7
 */
public abstract class BaseExtIdsEntityServiceImpl<T extends PBaseEntity> extends BaseEntityServiceImpl<T> implements BaseExtIdsEntityService<T> {

    @Override
    public List<T> selectByIds(String ids) {
        Assert.assertArgumentNotNull(ids, "ids");
        return getEntityMapper().selectByIds(ids);
    }

    @Override
    public int deleteByIds(String ids, OperateContext operCtx) {
        Assert.assertArgumentNotNull(ids, "ids");
        return getEntityMapper().deleteByIds(ids);
    }
}

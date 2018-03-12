package com.github.wolfclub.oneblog.service;

import com.github.wolfclub.oneblog.common.jpa.PBaseEntity;
import com.github.wolfclub.oneblog.commons.entity.OperateContext;

import java.util.List;

/**
 * @author liuchan
 * @date 11:00 2018/2/7
 */
public interface BaseExtIdsEntityService<T extends PBaseEntity> extends BaseEntityService<T>{

    /**
     * 根据ids查询集合
     * @param ids  用，分隔
     * @return
     */
    List<T>  selectByIds(String ids);

    /**
     * 根据ids删除
     * @param ids  用，分隔
     * @param operCtx
     * @return
     */
    int deleteByIds(String ids, OperateContext operCtx);
}

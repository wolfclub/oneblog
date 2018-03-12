package com.github.wolfclub.oneblog.mymapper;

import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author liuchan
 * @date 11:00 2018/2/7
 */
public interface MyMapper<T> extends Mapper<T>, IdsMapper<T>, AggregationMapper{
}

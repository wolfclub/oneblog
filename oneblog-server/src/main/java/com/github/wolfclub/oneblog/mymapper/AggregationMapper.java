package com.github.wolfclub.oneblog.mymapper;

import com.github.wolfclub.oneblog.commons.query.AggregateType;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

/**
 * @author liuchan
 * @date 14:00 2018/3/5
 */
public interface AggregationMapper {

	/**
	 * 根据Example条件进行聚合查询
	 *
	 * @param example
	 * @return
	 */
	@SelectProvider(type = AggregationProvider.class, method = "dynamicSQL")
	List<Map<String, Object>> selectAggregationByExample(@Param("example") Object example, @Param("aggregateType") AggregateType aggregateType, @Param("columnName") String columnName, @Param("groupBy") String groupBy);

}

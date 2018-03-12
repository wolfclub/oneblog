package com.github.wolfclub.oneblog.mymapper;

import org.apache.ibatis.mapping.MappedStatement;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;
import tk.mybatis.mapper.mapperhelper.SqlHelper;

/**
 * @author liuchan
 * @date 14:01 2018/3/5
 */
public class AggregationProvider extends MapperTemplate {

	public AggregationProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
		super(mapperClass, mapperHelper);
	}

	/**
	 * 根据Example查询总数
	 *
	 * @param ms
	 * @return
	 */
	public String selectAggregationByExample(MappedStatement ms) {
		Class<?> entityClass = getEntityClass(ms);
		StringBuilder sql = new StringBuilder();
		if (isCheckExampleEntityClass()) {
			sql.append(SqlHelper.exampleCheck(entityClass));
		}
		sql.append(aggregationSelectClause());
		sql.append(SqlHelper.fromTable(entityClass, tableName(entityClass)));
		sql.append(SqlHelper.updateByExampleWhereClause());
		sql.append(aggregationGroupBy());
		sql.append(SqlHelper.exampleForUpdate());
		return sql.toString();
	}


	public static String aggregationSelectClause() {
		return "SELECT " +
						"<if test=\"'avg'.toString() == aggregateType.toString()\"> AVG(${columnName}) </if>" +
						"<if test=\"'sum'.toString() == aggregateType.toString()\"> SUM(${columnName}) </if>" +
						"<if test=\"'count'.toString() == aggregateType.toString()\"> COUNT(${columnName}) </if>" +
						"<if test=\"'max'.toString() == aggregateType.toString()\"> MAX(${columnName}) </if>" +
						"<if test=\"'min'.toString() == aggregateType.toString()\"> MIN(${columnName}) </if>" +
						" AS value" +
						"<if test=\"groupBy != null and groupBy != ''\"> ,${groupBy} </if>";
	}

	public static String aggregationGroupBy() {
		return "<if test=\"groupBy != null and groupBy != ''\"> group by ${groupBy} </if>";
	}

}

package com.github.wolfclub.oneblog.commons.query;

/**
 * @author liuchan
 * @date 17:04 2018/3/9
 */
public class AggregateQueryFilter {

	/** 聚合函数 */
	private AggregateType aggregateType;
	/** 属性名 */
	private String propName;
	/** 分组 */
	private String groupBy;

	public AggregateType getAggregateType() {
		return aggregateType;
	}

	public AggregateQueryFilter setAggregateType(AggregateType aggregateType) {
		this.aggregateType = aggregateType;
		return this;
	}

	public String getPropName() {
		return propName;
	}

	public AggregateQueryFilter setPropName(String propName) {
		this.propName = propName;
		return this;
	}

	public String getGroupBy() {
		return groupBy;
	}

	public AggregateQueryFilter setGroupBy(String groupBy) {
		this.groupBy = groupBy;
		return this;
	}
}

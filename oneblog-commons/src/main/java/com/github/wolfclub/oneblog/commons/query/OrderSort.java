package com.github.wolfclub.oneblog.commons.query;

import java.io.Serializable;

/**
 * @author liuchan
 * @date 18:11 2018/3/1
 */
public class OrderSort implements Serializable{

	public static final String ASC = "ASC";
	public static final String DESC = "DESC";

	private String property;
	private String direction;

	public String getProperty() {
		return property;
	}

	public OrderSort setProperty(String property) {
		this.property = property;
		return this;
	}

	public String getDirection() {
		return direction;
	}

	public OrderSort setDirection(String direction) {
		this.direction = direction;
		return this;
	}
}

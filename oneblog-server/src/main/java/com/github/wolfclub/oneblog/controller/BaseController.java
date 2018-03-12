package com.github.wolfclub.oneblog.controller;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author liuchan
 * @date 16:25 2018/2/27
 */
public abstract class BaseController implements ApplicationContextAware{

	protected ApplicationContext getAppCtx() {
		return appCtx;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.appCtx = applicationContext;
	}

	private ApplicationContext appCtx;
}

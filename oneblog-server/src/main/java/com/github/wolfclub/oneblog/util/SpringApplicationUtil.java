package com.github.wolfclub.oneblog.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author liuchan
 * @date 16:27 2018/2/27
 */
public class SpringApplicationUtil {

	public static HttpSession getSession() {
		HttpSession session = null;
		try {
			session = getRequest().getSession();
		} catch (Exception e) {}
		return session;
	}

	public static HttpServletRequest getRequest() {
		ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder
						.getRequestAttributes();
		return attrs.getRequest();
	}

}

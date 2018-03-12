package com.github.wolfclub.oneblog.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author liuchan
 * @date 11:00 2018/2/7
 */
public class LoginFilter implements Filter {
    final static Logger logger = LoggerFactory.getLogger(LoginFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("LoginFilter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession(false);
//		logger.info(httpRequest.getRequestURI());
        if(session==null || session.getAttribute("currentUser")==null){
            HttpServletResponse HttpResponse = (HttpServletResponse)response;
            HttpResponse.sendRedirect(httpRequest.getContextPath()+"/");
            return;
        }

        //logger.info("没有登录");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        logger.info("LoginFilter init");
    }
}

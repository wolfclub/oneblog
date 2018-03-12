package com.github.wolfclub.oneblog.controller;

import com.github.wolfclub.oneblog.config.UserConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author liuchan
 * @date 11:00 2018/2/7
 */
@Controller
@EnableConfigurationProperties(value = UserConfigProperties.class)
public class LoginController {

    @Autowired
    private UserConfigProperties userConfig;

    @RequestMapping(value = "login")
    @ResponseBody
    public String login(HttpServletRequest request, HttpServletResponse response){

        if(userConfig.getLoginName().equals(request.getParameter("username"))){
            if(userConfig.getPassword().equals(request.getParameter("password"))){
                request.getSession().setAttribute("user", userConfig);
                return "登录成功";
            }
        }
        return "用户名或密码错误";
    }

}

package com.github.wolfclub.oneblog.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liuchan
 * @date 11:00 2018/2/7
 */
@RestController
public class TestController {

    @RequestMapping(value="/test", method = RequestMethod.GET)
    public String get(){
        System.out.println("get 请求");
        return "get 请求";
    }
    @RequestMapping(value="/test", method = RequestMethod.PUT)
    public String put(){
        System.out.println("put 请求");
        return "put 请求";
    }
    @RequestMapping(value="/test", method = RequestMethod.POST)
    public String post(){
        System.out.println("post 请求");
        return "post 请求";
    }
    @RequestMapping(value="/test", method = RequestMethod.DELETE)
    public String delete(){
        System.out.println("delete 请求");
        return "delete 请求";
    }
}

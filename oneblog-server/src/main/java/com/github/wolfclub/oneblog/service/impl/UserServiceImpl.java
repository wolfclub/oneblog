package com.github.wolfclub.oneblog.service.impl;

import com.github.wolfclub.oneblog.mapper.UserMapper;
import com.github.wolfclub.oneblog.mymapper.MyMapper;
import com.github.wolfclub.oneblog.po.User;
import com.github.wolfclub.oneblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liuchan
 * @date 11:00 2018/2/7
 */
@Service
public class UserServiceImpl extends BaseEntityServiceImpl<User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    protected MyMapper<User> getEntityMapper() {
        return userMapper;
    }

    @Override
    protected String getEntityCaption(User entity) {
        return "";
    }
}

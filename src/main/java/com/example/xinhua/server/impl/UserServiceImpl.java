package com.example.xinhua.server.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.xinhua.mapper.UserMapper;
import com.example.xinhua.pojo.UserPojo;
import com.example.xinhua.server.UserService;
import com.example.xinhua.utils.Md5Util;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Object getUserById(Integer id) {
        return userMapper.getUserById(id);
    }

    @Override
    public UserPojo getUserByName(String name) {
        return userMapper.getUserByName(name);
    }

    @Override
    public void insertUser(String name, String pwd) {
        // TODO Auto-generated method stub
        userMapper.insert(name, Md5Util.getMD5String(pwd));
    }

    @Override
    public void update(UserPojo user) {
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }

}

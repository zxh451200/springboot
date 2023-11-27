package com.example.xinhua.server.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.xinhua.mapper.UserMapper;
import com.example.xinhua.pojo.PageBean;
import com.example.xinhua.pojo.UserPojo;
import com.example.xinhua.server.UserService;
import com.example.xinhua.utils.Md5Util;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

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

    @Override
    public PageBean<UserPojo> getList(Integer page, Integer limit, String name) {

        // 创建对象
        PageBean<UserPojo> pd = new PageBean<>();

        // 开启分页
        PageHelper.startPage(page, limit);
        
        List<UserPojo> userLists = userMapper.getList(name);
        System.out.println(name);
        System.out.println(userLists);
        Page<UserPojo> p = (Page<UserPojo>) userLists;
        
        pd.setTotal(p.getTotal());
        pd.setItems(p.getResult());
        return pd;
    }

}

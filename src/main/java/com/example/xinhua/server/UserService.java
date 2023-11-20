package com.example.xinhua.server;

import com.example.xinhua.pojo.UserPojo;

public interface UserService {

    Object getUserById(Integer id);

    UserPojo getUserByName(String name);

    void insertUser(String name, String pwd);

    void update(UserPojo user);

}

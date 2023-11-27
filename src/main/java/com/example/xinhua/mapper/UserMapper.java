package com.example.xinhua.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.xinhua.pojo.UserPojo;

@Mapper
public interface UserMapper {
    
    @Select("select * from user where id = #{id}")
    public UserPojo getUserById(Integer id);

    @Select("select * from user where name = #{name}")
    public UserPojo getUserByName(String name);

    @Insert("insert into user (name,password,create_time,update_time) values (#{name},#{password},now(),now())")
    public void insert(String name, String password);

    @Update("update user set name=#{name},age=#{age},update_time=#{updateTime} where id=#{id}")
    public void update(UserPojo user);

    public List<UserPojo> getList(String name);

}

package com.example.xinhua.controller;

import com.example.xinhua.utils.JwtUtil;
import com.example.xinhua.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.xinhua.pojo.Result;
import com.example.xinhua.pojo.UserPojo;
import com.example.xinhua.server.UserService;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@Validated
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/getUserById/{id}")
    public Result getUserById(@PathVariable Integer id) {
        System.out.println(1111);
        System.out.println(id);
        return Result.success("成功", userService.getUserById(id));
    }

    @PostMapping("/login")
    public Result register(@Pattern(regexp = "^\\S{2,5}$") String name, String pwd) {
        System.out.println(name);
        UserPojo user = userService.getUserByName(name);
        if (user != null) {
            return Result.error("用户名已经占用");
        }
        userService.insertUser(name, pwd);
        return Result.success("成功", null);
    }

    @GetMapping("/getToken")
    public Result getToken() {
        HashMap<String, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("name", "xin");
        objectObjectHashMap.put("pwd", "123");
        String token = JwtUtil.getToken(objectObjectHashMap);
        return Result.success("成功", token);
    }

    @GetMapping("/removeToken")
    public void removeToken() {
        JwtUtil.removeToken();
    }

    @GetMapping("/userInfo")
    public Result<UserPojo> getUserInfo(String xxx) {
        System.out.println(xxx);
        Map<String, Object> ThreadLocalMap = ThreadLocalUtil.get();
        String name = (String) ThreadLocalMap.get("name");
        UserPojo user = userService.getUserByName(name);
        System.out.println(name);
        System.out.println(ThreadLocalMap);
        return Result.success("成功", user);
    }

    @PutMapping("/update")
    public void update(@RequestBody @Validated UserPojo user) {
        userService.update(user);
    }
}

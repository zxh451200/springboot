package com.example.xinhua.controller;

import com.example.xinhua.utils.JwtUtil;
import com.example.xinhua.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.xinhua.pojo.PageBean;
import com.example.xinhua.pojo.Result;
import com.example.xinhua.pojo.UserPojo;
import com.example.xinhua.server.UserService;
import jakarta.validation.constraints.Pattern;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@Validated
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    // 路径变量
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
        String token = "Bearer " + JwtUtil.getToken(objectObjectHashMap);

        redisTemplate.opsForValue().set("token", token, 1, TimeUnit.DAYS);
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
    public void update(@RequestBody @Validated(UserPojo.Update.class) UserPojo user) {
        System.out.println(user);
        userService.update(user);
    }

    @GetMapping("/getList/{page}/{limit}")
    public Result<PageBean<UserPojo>> getList(@PathVariable Integer page, @PathVariable Integer limit,
            @RequestParam(required = false) String name) {
        PageBean<UserPojo> list = userService.getList(page, limit, name);
        return Result.success("成功", list);
    }

    // 递归反转字符串
    public void  resvStr(int index,String str){
        if (str.length()==index){
            return;
        }
        resvStr(index + 1, str);
        System.out.println(str.charAt(index));
    }

    // 二分查找
    public static int binarySearch(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (array[mid] == target) {
                return mid;
            } else if (array[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
    }

    // 冒泡 right是待排的又边界
    public void maopao(int right, int[] arr) {
        if (right==0){
            return;
        }
        for (int i = 0; i < right; i++) {
            if (arr[i] < arr[i + 1]) {
                int tmp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = tmp;
                x = i;
            }
        }
        maopao(x,arr);
    }

     // 递归优化  记忆法也叫备忘录
    public int feibonaqie(int n) {
        int[] cache = new int[n + 1];
        Arrays.fill(cache,-1);
        cache[0]=0;
        cache[1]=1;
        return digui(n, cache);

    }

    public int digui(int n, int[] cache) {
        if (n <= 1) {
            return n;
        }
        cache[n] = digui(n - 1, cache) + digui(n - 2, cache);
        return cache[n];
    }

}

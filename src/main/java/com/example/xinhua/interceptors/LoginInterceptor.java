package com.example.xinhua.interceptors;

import com.example.xinhua.pojo.Result;
import com.example.xinhua.utils.JwtUtil;
import com.example.xinhua.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import com.alibaba.fastjson.JSONObject;

import java.io.PrintWriter;
import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        try {
            Map<String, Object> clsims = JwtUtil.parseToken(token);
            System.out.println("拦截器生效");
            System.out.println(clsims);
            ThreadLocalUtil.set(clsims);
            return true;
        } catch (Exception e) {
            response.setStatus(400);
            String jsonObjectStr = JSONObject.toJSONString(Result.error("拦截器拦截了"));
            returnJson(response, jsonObjectStr);
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ThreadLocalUtil.remove();
    }

    @SuppressWarnings("unused")
    private void returnJson(HttpServletResponse response, String json) throws Exception {
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/json; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(json);

        } catch (Exception e) {
        } finally {
            if (writer != null)
                writer.close();
        }
    }
}

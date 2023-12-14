package com.example.xinhua.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.xinhua.pojo.Result;
import com.example.xinhua.server.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping(value = "/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    /// getList/1/10
    @GetMapping("/getList")
    public Result getOrderList(@PathVariable int page, @PathVariable int limit) {
        orderService.getList();
        return new Result(true, "success", null);
    }
}

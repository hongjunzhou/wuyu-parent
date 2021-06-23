package com.wuyu.user.controller;

import com.wuyu.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
@RequestMapping("/order")
public class OrderController {


    @GetMapping("/getOrderInfo")
    public String getAccountInfo(Long orderId){
        return "订单ID："+orderId;
    }

}

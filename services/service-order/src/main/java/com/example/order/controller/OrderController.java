package com.example.order.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.example.order.bean.Order;
import com.example.order.properties.OrderProperties;
import com.example.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
public class OrderController {
    @Autowired
    OrderService orderService;

    @Autowired
    OrderProperties orderProperties;

    @GetMapping("/config")
    public String getConfig() {
        return orderProperties.getTimeout() + "：" + orderProperties.getAutoConfirm() + "：" + orderProperties.getDbUrl();
    }

    @GetMapping("/create")
    public Order createOrder(@RequestParam("userId") Long userId,
                             @RequestParam("productId") Long productId) {
        Order order = orderService.createOrder(userId, productId);
        return order;
    }

    @GetMapping("/seckill")
    @SentinelResource(value = "seckill-order",fallback = "seckillFallback")
    public Order seckill(@RequestParam(value = "userId",required = false) Long userId,
                         @RequestParam(value = "productId",defaultValue = "888") Long productId) {
        Order order = orderService.createOrder(userId, productId);
        order.setId(Long.MAX_VALUE);
        return order;
    }

    public Order seckillFallback(Long userId, Long productId, Throwable e) {
        System.out.println("seckillFallback.....");
        Order order = new Order();
        order.setId(productId);
        order.setUserId(userId);
        order.setAddress("异常信息：" + e.getClass());
        return order;
    }

    @GetMapping("/writeDb")
    public String writeDb() {
        return "writeDb success.....";
    }

    @GetMapping("/readDb")
    public String readDb() {
        log.info("readDb success....");
        return "readDb success.....";
    }
}

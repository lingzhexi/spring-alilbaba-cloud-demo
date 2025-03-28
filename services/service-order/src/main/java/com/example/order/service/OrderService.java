package com.example.order.service;

import com.example.order.bean.Order;

public interface OrderService {
    Order createOrder(Long productId, Long userId);
}

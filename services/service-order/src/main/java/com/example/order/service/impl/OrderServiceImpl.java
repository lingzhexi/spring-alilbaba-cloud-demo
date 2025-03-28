package com.example.order.service.impl;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import com.example.order.bean.Order;
import com.example.order.service.OrderService;
import com.example.product.bean.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    RestTemplate restTemplate;

    public Order createOrder(Long productId,Long userId) {
        Product product = getProduct(productId);
        Order order = new Order();
        order.setId(1L);
        order.setTotalAmount(product.getPrice().multiply(new BigDecimal(product.getNum())));
        order.setUserId(userId);
        order.setNickName("lingzx");
        order.setAddress("恒大城");
        order.setProductList(Arrays.asList(product));
        return order;
    }

    private Product getProductFromRemote(Long productId) {
        List<ServiceInstance> instances = discoveryClient.getInstances("service-product");
        ServiceInstance serviceInstance = instances.get(0);
        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/product/" + productId;
        log.info("远程调用：{}", url);

        Product product = restTemplate.getForObject(url, Product.class);
        return product;

    }

    private Product getProduct(Long productId) {
        String url = "http://service-product/product/" + productId;
        Product product = restTemplate.getForObject(url, Product.class);
        return product;
    }
}

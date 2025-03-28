package com.example.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;

@SpringBootTest
public class LoadBalancerTest {

    @Autowired
    LoadBalancerClient loadBalancerClient;
    DiscoveryClient discoveryClient;

    void test() {

    }
}

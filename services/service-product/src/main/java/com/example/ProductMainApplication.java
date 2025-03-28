package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient //服务发现
@SpringBootApplication
public class ProductMainApplication {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        SpringApplication.run(ProductMainApplication.class, args);
    }
}
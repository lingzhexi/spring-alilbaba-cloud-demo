package com.example.service.impl;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import ch.qos.logback.core.util.TimeUtil;
import com.example.product.bean.Product;
import com.example.service.ProductService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @SneakyThrows
    @Override
    public Product getProduct(Long productId) {
        Product product = new Product();
        product.setId(productId);
        product.setPrice(new BigDecimal("99"));
        product.setProductName("苹果-" + product);
        product.setNum(2);
        TimeUnit.SECONDS.sleep(100);
        return product;
    }
}

package com.example.service.impl;

import java.math.BigDecimal;

import com.example.product.bean.Product;
import com.example.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Override
    public Product getProduct(Long productId) {
        Product product = new Product();
        product.setId(productId);
        product.setPrice(new BigDecimal("99"));
        product.setProductName("苹果-" + product);
        product.setNum(2);
        return product;
    }
}

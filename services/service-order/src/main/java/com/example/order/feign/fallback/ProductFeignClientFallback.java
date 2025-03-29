package com.example.order.feign.fallback;

import com.example.order.feign.ProductFeignClient;
import com.example.product.bean.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Component
public class ProductFeignClientFallback implements ProductFeignClient {

    @Override
    public Product getProductById(Long id) {
        System.out.println("兜底数据");//如果调用接口失败了,一单feign调用失败,就会调用这个方法
        Product product = new Product();
        product.setId(1L);
        product.setPrice(new BigDecimal("0"));
        product.setProductName("未知商品");
        product.setNum(0);
        return product;
    }
}

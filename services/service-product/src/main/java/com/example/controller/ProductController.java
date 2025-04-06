package com.example.controller;

import com.example.product.bean.Product;
import com.example.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @SneakyThrows
    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable("id")Long productId,
                              HttpServletRequest request) {
        String header = request.getHeader("X-Token");
        System.out.println(header);
        System.out.println("welcome");
        int i = 1/0;
//        TimeUnit.SECONDS.sleep(2000);
        return productService.getProduct(productId);
    }

}

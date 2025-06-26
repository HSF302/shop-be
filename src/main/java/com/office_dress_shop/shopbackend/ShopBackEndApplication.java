package com.office_dress_shop.shopbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
//
//@SpringBootApplication(scanBasePackages = "com.office_dress_shop.shopbackend")
//@EntityScan(basePackages = "com.office_dress_shop.shopbackend.pojo")
@SpringBootApplication
public class ShopBackEndApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShopBackEndApplication.class, args);
    }
}
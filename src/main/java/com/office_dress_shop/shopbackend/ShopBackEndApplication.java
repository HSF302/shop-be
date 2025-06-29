package com.office_dress_shop.shopbackend;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
//
//@SpringBootApplication(scanBasePackages = "com.office_dress_shop.shopbackend")
//@EntityScan(basePackages = "com.office_dress_shop.shopbackend.pojo")
@SpringBootApplication
@SecurityScheme(name = "api",scheme = "bearer", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
public class ShopBackEndApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShopBackEndApplication.class, args);
    }
}
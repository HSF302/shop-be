package com.office_dress_shop.shopbackend;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.office_dress_shop.shopbackend.repository")
@EntityScan(basePackages = "com.office_dress_shop.shopbackend.pojo")
//@SecurityScheme(name = "api",scheme = "bearer", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
public class ShopBackEndApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShopBackEndApplication.class, args);
    }
}
package com.office_dress_shop.shopbackend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Map URL path /images/uploads/** to the actual folder: [project-root]/images/uploads/
        registry.addResourceHandler("/images/uploads/**")
                .addResourceLocations("file:" + System.getProperty("user.dir") + "/images/uploads/");
    }
}

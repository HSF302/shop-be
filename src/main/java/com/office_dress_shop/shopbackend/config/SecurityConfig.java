package com.office_dress_shop.shopbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/register", "/forgot-password", "/reset-password").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login") // Chỉ ra trang login custom của bạn
                        .defaultSuccessUrl("/home", true)
                        .failureUrl("/login?error=true") // Hiển thị thông báo lỗi
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login") // Redirect về trang login sau khi logout
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                ).httpBasic().disable()
                .formLogin().disable();  // Disable form login mặc định khi bạn tự xử lý đăng nhập
        return http.build();
    }
}


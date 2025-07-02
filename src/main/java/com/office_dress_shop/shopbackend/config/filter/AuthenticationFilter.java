package com.office_dress_shop.shopbackend.config.filter;

import com.office_dress_shop.shopbackend.pojo.Account;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String path = request.getRequestURI();

        // Các đường dẫn public
        if (isPublicPath(path)) {
            filterChain.doFilter(request, response);
            return;
        }

        HttpSession session = request.getSession(false);
        Account account = session != null ? (Account) session.getAttribute("account") : null;

        if (account == null) {
            response.sendRedirect("/auth/login");
            return;
        }

        // Phân quyền admin cho /admin/**
        if (path.startsWith("/accounts/list") && (account.getRole() == null || !account.getRole().name().equals("ADMIN"))) {
            response.sendRedirect("/error");
            return;
        }

        filterChain.doFilter(request, response);
    }

    private boolean isPublicPath(String path) {
        return path.startsWith("/auth/") ||
                path.startsWith("/css/") ||
                path.startsWith("/js/") ||
                path.startsWith("/images/") ||
                path.equals("/") ||
                path.equals("/error") ||
                path.equals("/home");
    }
}

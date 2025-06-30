package com.office_dress_shop.shopbackend.filter;

import com.office_dress_shop.shopbackend.enums.Role;
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

        // Allow static resources and basic endpoints
        if (path.startsWith("/css/") ||
                path.startsWith("/js/") ||
                path.startsWith("/images/") ||
                path.startsWith("/auth/") ||
                path.equals("/") ||
                path.equals("/error")) {
            filterChain.doFilter(request, response);
            return;
        }

        HttpSession session = request.getSession(false);

        // Check admin role for admin paths
        if (path.startsWith("/admin/accounts")) {
            Account account = (Account) session.getAttribute("account");
            if (account == null || account.getRole() != Role.ADMIN) {
                System.out.println("Unauthorized access attempt to admin path: " + path);
                response.sendRedirect("/error");
                return;
            }
        }

        // Check session for other requests
        if (session == null || session.getAttribute("account") == null) {
            System.out.println("No valid session found. Redirecting to 403. Path: " + path);
            response.sendRedirect("/error");
            return;
        }

        System.out.println("Session valid, proceeding with request to: " + path);
        filterChain.doFilter(request, response);
    }
}
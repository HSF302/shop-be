package com.office_dress_shop.shopbackend.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

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
                path.equals("/error")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Check session for other requests
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("account") == null) {
//            System.out.println("No valid session found. Redirecting to 403. Path: " + path);
            response.sendRedirect("/error");
            return;
        }

//        System.out.println("Session valid, proceeding with request to: " + path);
        filterChain.doFilter(request, response);
    }
}
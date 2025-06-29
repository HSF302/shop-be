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
                    path.equals("/login") ||
                    path.equals("/register") ||
                    path.equals("/forgot-password") ||
                    path.equals("/reset-password") ||
                    path.equals("/403") ||
                    path.equals("/error")) {
                    filterChain.doFilter(request, response);
                    return;
                }

                // Check session for other requests
                HttpSession session = request.getSession(false);
                if (session == null || session.getAttribute("account") == null) {
                    System.out.println("No valid session found. Redirecting to 403. Path: " + path);
                    response.sendRedirect("/403");
                    return;
                }

                System.out.println("Session valid, proceeding with request to: " + path);
                filterChain.doFilter(request, response);
            }
        }
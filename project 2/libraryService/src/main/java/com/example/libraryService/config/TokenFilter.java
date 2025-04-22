package com.example.libraryService.config;


import com.example.libraryService.interceptors.RequestContext;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import jakarta.servlet.FilterChain;

import java.io.IOException;

@Component
public class TokenFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        // Extract token from Authorization header
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7); // Remove "Bearer " prefix
            RequestContext.setToken(token);
        }

        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            // Clear the token after the request is processed
            RequestContext.clear();
        }
    }
}

package com.fdm.pmsuibackend.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fdm.pmsuibackend.service.security.CustomizedUserDetailsService;
import com.fdm.pmsuibackend.service.security.JWTService;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;

import org.springframework.stereotype.Component;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTFilter extends OncePerRequestFilter {

    private final JWTService jwtService;
    private final CustomizedUserDetailsService customizedUserDetailsService;

    public JWTFilter(JWTService jwtService, CustomizedUserDetailsService customizedUserDetailsService) {
        this.jwtService = jwtService;
        this.customizedUserDetailsService = customizedUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;

        try {

            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                token = authHeader.substring(7);
                username = jwtService.extractUsername(token);
            }

            // Check if the current authentication is expired
            Authentication curAuth = SecurityContextHolder.getContext().getAuthentication();
            if (curAuth != null) {
                String curToken = curAuth.getCredentials().toString();
                if (curToken != null && jwtService.isTokenExpired(curToken)) {
                    System.out.println("Token expired");
                    SecurityContextHolder.clearContext();
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.getWriter().write("Token expired");
                    return; // Stop further processing
                }
            }

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                UserDetails userDetails = customizedUserDetailsService.loadUserByUsername(username);
                if (jwtService.validateToken(token, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
                            token, userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        } catch (SignatureException | ExpiredJwtException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException e) {
            SecurityContextHolder.clearContext();
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            
            Map<String, String> error = new HashMap<>();
            error.put("message", "Invalid token: " + e.getMessage());
            
            ObjectMapper mapper = new ObjectMapper();
            String jsonResponse = mapper.writeValueAsString(error);
            
            response.getWriter().write(jsonResponse);
        }
        filterChain.doFilter(request, response);
    }

}


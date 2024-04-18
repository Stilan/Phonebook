package com.example.phonebook.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.phonebook.entity.User;
import com.example.phonebook.entity.UserLoginDetails;
import com.example.phonebook.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.contains("Bearer ")) {
            String token = authHeader.substring(7);
            if(!token.isBlank()) {
                DecodedJWT decodedJWT =  JWT.require(Algorithm.HMAC256(JwtService.SECRET))
                        .build()
                        .verify(token);
                User user = User.builder()
                        .name(decodedJWT.getClaim("Name").asString())
                        .password(decodedJWT.getClaim("Password").asString())
                        .id(UUID.fromString(decodedJWT.getClaim("Id").asString()))
                        .build();
                UserLoginDetails userLoginDetails = new UserLoginDetails(user);
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(user, null, userLoginDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }

}

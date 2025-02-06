package com.selvaragavan.afterthecontestapi.authentication.filters;

import com.selvaragavan.afterthecontestapi.authentication.Entities.User;
import com.selvaragavan.afterthecontestapi.authentication.services.JWTService;
import com.selvaragavan.afterthecontestapi.exceptions.TokenExpiredException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    JWTService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");

        if(token == null || !token.startsWith("Bearer")) {
            filterChain.doFilter(request, response);
            return;
        }

        token = token.substring(7);
        System.out.println(token);
        if(jwtService.isTokenExpired(token)){
            throw new TokenExpiredException();
        }

        UserDetails user = jwtService.extractClaim(token,(a)->{
            return (UserDetails) new User((String)a.get("username"),null,(String)a.get("email"));
        });

        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(auth); //auth object set so no problem.

        filterChain.doFilter(request, response);
    }
}

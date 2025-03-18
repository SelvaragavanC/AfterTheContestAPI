package com.selvaragavan.afterthecontestapi.authentication.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.selvaragavan.afterthecontestapi.authentication.Entities.User;
import com.selvaragavan.afterthecontestapi.authentication.services.JWTService;
import io.jsonwebtoken.ExpiredJwtException;
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

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    JWTService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            String token = request.getHeader("Authorization");

            if(token == null || !token.startsWith("Bearer")) {
                filterChain.doFilter(request, response);
                return;
            }

            token = token.substring(7);
            jwtService.isTokenExpired(token); // this will throw exception while parsing claims

            UserDetails user = jwtService.extractClaim(token,(a)->{
                return (UserDetails) new User((String)a.get("username"),null,(String)a.get("email"));
            });

            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(auth); //auth object set so no problem.

            filterChain.doFilter(request, response);
        }catch(ExpiredJwtException e){
            response.setStatus(401);
            response.setContentType("application/json");

            HashMap<String,String> mp = new HashMap<>();
            mp.put("error","Expired JWT token");
            mp.put("message","Your Session Expired, Please Login");

            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getWriter(),mp);
        }
    }
}

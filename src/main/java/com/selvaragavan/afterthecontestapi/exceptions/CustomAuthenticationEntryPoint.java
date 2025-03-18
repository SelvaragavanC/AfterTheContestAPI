package com.selvaragavan.afterthecontestapi.exceptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(401);
        response.setContentType("application/json");
        HashMap<String,String> mp = new HashMap<>();
        mp.put("error", "Access denied");
        mp.put("message", "Please login to continue");

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(), mp);
    }
}

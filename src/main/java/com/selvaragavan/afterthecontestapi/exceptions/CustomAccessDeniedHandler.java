package com.selvaragavan.afterthecontestapi.exceptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;
import java.util.HashMap;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(401);
        response.setContentType("application/json");
        HashMap<String,String> mp = new HashMap<>();
        mp.put("error", "Access denied");
        mp.put("message", "You don't have permission to access this resource, Please login!");

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(), mp);
    }
}

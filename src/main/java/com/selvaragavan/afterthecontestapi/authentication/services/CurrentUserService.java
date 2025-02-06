package com.selvaragavan.afterthecontestapi.authentication.services;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserService {
    public String getCurrentUserName(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth != null){return null;}
        String name = auth.getName();
        return name;
    }
}


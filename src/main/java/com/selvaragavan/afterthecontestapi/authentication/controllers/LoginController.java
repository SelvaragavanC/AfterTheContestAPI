package com.selvaragavan.afterthecontestapi.authentication.controllers;

import com.selvaragavan.afterthecontestapi.authentication.dto.LoginRequestDTO;
import com.selvaragavan.afterthecontestapi.authentication.dto.LoginResponseDTO;
import com.selvaragavan.afterthecontestapi.authentication.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @PostMapping("/login")
    public LoginResponseDTO LoginController(@RequestBody LoginRequestDTO request) {
        return userDetailsService.loginUser(request);
    }
}

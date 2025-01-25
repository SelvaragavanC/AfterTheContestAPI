package com.selvaragavan.afterthecontestapi.authentication.controllers;

import com.selvaragavan.afterthecontestapi.authentication.dto.RegisterRequestDTO;
import com.selvaragavan.afterthecontestapi.authentication.dto.RegisterResponseDTO;
import com.selvaragavan.afterthecontestapi.authentication.services.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @PostMapping("/register")
    public RegisterResponseDTO registerUser(@RequestBody RegisterRequestDTO request) {
        return registerService.register(request);
    }
}

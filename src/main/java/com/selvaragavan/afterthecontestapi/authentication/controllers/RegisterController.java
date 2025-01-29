package com.selvaragavan.afterthecontestapi.authentication.controllers;

import com.selvaragavan.afterthecontestapi.authentication.dto.RegisterRequestDTO;
import com.selvaragavan.afterthecontestapi.authentication.dto.RegisterResponseDTO;
import com.selvaragavan.afterthecontestapi.authentication.services.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @PostMapping("/register")
    public RegisterResponseDTO registerUser(@RequestBody RegisterRequestDTO request) {
        return registerService.register(request);
    }

    @GetMapping("/register/verify")
    public ResponseEntity<String> verifyUser(@RequestParam("token") String token) {
        return new ResponseEntity<>(registerService.verify(token), HttpStatus.ACCEPTED);
    }
}

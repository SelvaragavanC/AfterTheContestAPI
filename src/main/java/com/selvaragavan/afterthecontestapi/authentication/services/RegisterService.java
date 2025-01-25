package com.selvaragavan.afterthecontestapi.authentication.services;

import com.selvaragavan.afterthecontestapi.authentication.Entities.RegisteredUser;
import com.selvaragavan.afterthecontestapi.authentication.dto.RegisterRequestDTO;
import com.selvaragavan.afterthecontestapi.authentication.dto.RegisterResponseDTO;
import com.selvaragavan.afterthecontestapi.authentication.repositories.RegisterUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    RegisterUserRepository registerUserRepository;


    public RegisterResponseDTO register(RegisterRequestDTO request) {
        String username = request.getUsername();
        String email = request.getEmail();
        String password = request.getPassword();

        if(userDetailsService.loadUserByUsername(username) != null){
            return new RegisterResponseDTO("Username Already exists");
        }

        if(userDetailsService.loadUserByUsername(email) != null){
            return new RegisterResponseDTO("Email already exists");
        }

        String token = "SummaToken"; // i should generate token
        // I should write code to send token to email.

        RegisteredUser user = new RegisteredUser(username, email, password, token);
        registerUserRepository.save(user);

        return new RegisterResponseDTO("A link has been sent to your email, Follow through the link ");
    }
}

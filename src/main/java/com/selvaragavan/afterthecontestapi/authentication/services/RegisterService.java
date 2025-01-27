package com.selvaragavan.afterthecontestapi.authentication.services;

import com.selvaragavan.afterthecontestapi.authentication.Entities.RegisteredUser;
import com.selvaragavan.afterthecontestapi.authentication.dto.RegisterRequestDTO;
import com.selvaragavan.afterthecontestapi.authentication.dto.RegisterResponseDTO;
import com.selvaragavan.afterthecontestapi.authentication.repositories.RegisterUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RegisterService {
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    RegisterUserRepository registerUserRepository;

    @Autowired
    JWTService jwtService;

    @Autowired
    MailService mailService;

    @Value("${spring.application.frontend-url}")
    private String URL;

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

        Map<String,Object> claims = new HashMap<>();
        claims.put("username", username);
        claims.put("email", email);
        claims.put("password", password);

        String token = jwtService.generateToken(username,claims);
        String subject = "AfterTheContest - Email verification";
        String content = "<button href='"+ URL + token +"'> Click Here</button>";

        mailService.sendEmail(email,subject,content);

        RegisteredUser user = new RegisteredUser(username, email, password, token);
        registerUserRepository.save(user);

        return new RegisterResponseDTO("A link has been sent to your email, Follow through the link ");
    }
}

package com.selvaragavan.afterthecontestapi.authentication.services;

import com.selvaragavan.afterthecontestapi.authentication.Entities.RegisteredUser;
import com.selvaragavan.afterthecontestapi.authentication.Entities.User;
import com.selvaragavan.afterthecontestapi.authentication.dto.RegisterRequestDTO;
import com.selvaragavan.afterthecontestapi.authentication.dto.RegisterResponseDTO;
import com.selvaragavan.afterthecontestapi.authentication.repositories.RegisterUserRepository;
import com.selvaragavan.afterthecontestapi.authentication.repositories.UserRepository;
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

    @Autowired
    private UserRepository userRepository;

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
        String content = "<h1> click <a href = '"+ URL + "/register/verify?token="+token+"'> here</a> </h1>";

        mailService.sendEmail(email,subject,content);

        RegisteredUser user = new RegisteredUser(username, email, password, token);
        registerUserRepository.save(user);

        return new RegisterResponseDTO("A link has been sent to your email, Follow through the link ");
    }

    public String verify(String token) {
        String username = jwtService.extractUsername(token);
        System.out.println(username);
        RegisteredUser rUser;
        if((rUser = registerUserRepository.findByUsername(username)) != null){
            String email = rUser.getEmail();
            String password = rUser.getPassword();
            User user = new User(username,password,email);
            userRepository.save(user);
            registerUserRepository.delete(rUser);
            return "You are successfully verified. Go to " + URL ;
        }else{
            return "You are not verified";
        }
    }
}

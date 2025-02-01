package com.selvaragavan.afterthecontestapi.authentication.services;

import com.selvaragavan.afterthecontestapi.authentication.dto.LoginRequestDTO;
import com.selvaragavan.afterthecontestapi.authentication.dto.LoginResponseDTO;
import com.selvaragavan.afterthecontestapi.authentication.repositories.UserRepository;
import com.selvaragavan.afterthecontestapi.exceptions.InvalidCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    JWTService jwtService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public UserDetails loadByEmail(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email);
    }

    public LoginResponseDTO loginUser(LoginRequestDTO loginRequestDTO) throws InvalidCredentialsException {
        String username = loginRequestDTO.getUsername();
        String email = loginRequestDTO.getEmail();
        String password = loginRequestDTO.getPassword();

        UserDetails user;
        if(username!=null && (user = loadUserByUsername(username))!=null && password.equals(user.getPassword())) {
            Map<String,Object> claims = new HashMap<>();
            claims.put("username",user.getUsername());
            String token = jwtService.generateToken(user.getUsername(),claims);
            return new LoginResponseDTO(token,"You have successfully logged in");
        }

        if(email!=null && (user = loadByEmail(email))!=null && password.equals(user.getPassword())) {
            Map<String,Object> claims = new HashMap<>();
            claims.put("username",user.getUsername());
            String token = jwtService.generateToken(user.getUsername(),claims);
            return new LoginResponseDTO(token,"You have successfully logged in");
        }

        throw new InvalidCredentialsException("Invalid credentials");
    }
}

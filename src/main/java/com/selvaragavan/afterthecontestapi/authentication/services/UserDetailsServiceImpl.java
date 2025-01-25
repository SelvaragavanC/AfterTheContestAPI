package com.selvaragavan.afterthecontestapi.authentication.services;

import com.selvaragavan.afterthecontestapi.authentication.dto.RegisterRequestDTO;
import com.selvaragavan.afterthecontestapi.authentication.dto.RegisterResponseDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    public UserDetails loadByEmail(String email) throws UsernameNotFoundException {
        return null;
    }

}

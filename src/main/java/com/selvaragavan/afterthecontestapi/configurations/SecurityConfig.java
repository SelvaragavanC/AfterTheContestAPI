package com.selvaragavan.afterthecontestapi.configurations;

import com.selvaragavan.afterthecontestapi.authentication.filters.JWTAuthenticationFilter;
import com.selvaragavan.afterthecontestapi.exceptions.CustomAccessDeniedHandler;
import com.selvaragavan.afterthecontestapi.exceptions.CustomAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    JWTAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    CustomAccessDeniedHandler customAccessDeniedHandler;

    @Autowired
    CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(customizer -> {
                    customizer.requestMatchers("/register","/register/verify","/login").permitAll();
                    customizer.anyRequest().authenticated();
                })
                .exceptionHandling(customizer -> customizer.accessDeniedHandler(customAccessDeniedHandler))
                .exceptionHandling(customizer -> customizer.authenticationEntryPoint(customAuthenticationEntryPoint))
                .build();
    }
}

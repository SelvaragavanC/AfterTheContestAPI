package com.selvaragavan.afterthecontestapi.authentication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterRequestDTO {
    private String username;
    private String password;
    private String email;
    
}

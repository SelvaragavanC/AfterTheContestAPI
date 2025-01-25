package com.selvaragavan.afterthecontestapi.authentication.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisteredUser {
    @Id
    public String username;
    public String email;
    public String password;
    public String token;
}

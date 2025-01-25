package com.selvaragavan.afterthecontestapi.authentication.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class User {
    @Id
    public String username;
    public String password;
    public String email;
}

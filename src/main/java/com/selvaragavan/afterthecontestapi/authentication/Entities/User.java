package com.selvaragavan.afterthecontestapi.authentication.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    public String username;
    public String password;
    public String email;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(); //there is no roles in my application
    }
}

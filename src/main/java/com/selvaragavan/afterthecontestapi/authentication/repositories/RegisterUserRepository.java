package com.selvaragavan.afterthecontestapi.authentication.repositories;

import com.selvaragavan.afterthecontestapi.authentication.Entities.RegisteredUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisterUserRepository extends JpaRepository<RegisteredUser,String> {
    public RegisteredUser findByEmail(String email);
}

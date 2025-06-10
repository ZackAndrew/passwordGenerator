package com.zack.passwordGenerator.service;

import com.zack.passwordGenerator.config.SecurityConfig;
import com.zack.passwordGenerator.model.Users;
import com.zack.passwordGenerator.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    UserRepo repo;

    @Autowired
    private PasswordEncoder encoder;

    public Users registerUser(Users user) {
        user.setPassword_hash(encoder.encode(user.getPassword_hash()));
        return repo.save(user);
    }
}

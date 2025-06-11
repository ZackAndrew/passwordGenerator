package com.zack.passwordGenerator.service;

import com.zack.passwordGenerator.model.LoginRequest;
import com.zack.passwordGenerator.model.Users;
import com.zack.passwordGenerator.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    UserRepo repo;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JWTService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    public Users registerUser(Users user) {
        user.setPassword_hash(encoder.encode(user.getPassword_hash()));
        return repo.save(user);
    }

    public String verify(LoginRequest loginRequest) {
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        if (authentication.isAuthenticated())
            return jwtService.generateToken(loginRequest.getUsername());

        return "fail";
    }
}

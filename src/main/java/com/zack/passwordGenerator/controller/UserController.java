package com.zack.passwordGenerator.controller;

import com.zack.passwordGenerator.model.Users;
import com.zack.passwordGenerator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService service;

    @GetMapping("/")
    public String greet() {
        return "Hello via my web-site";
    }

    @PostMapping("/register")
    public ResponseEntity<Users> registerUser(@RequestBody Users user) {
        Users encryptedUser = service.registerUser(user);
        return new ResponseEntity<>(encryptedUser, HttpStatus.OK);
    }
}

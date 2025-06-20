package com.zack.passwordGenerator.controller;

import com.zack.passwordGenerator.model.LoginRequest;
import com.zack.passwordGenerator.model.Users;
import com.zack.passwordGenerator.service.EmailService;
import com.zack.passwordGenerator.service.UserService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService service;

    private final EmailService emailService;

    public UserController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/register")
    public ResponseEntity<Users> registerUser(@RequestBody Users user) {
        Users encryptedUser = service.registerUser(user);

        try {
            emailService.greetingEmail(encryptedUser);
        } catch (MessagingException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(encryptedUser);
        }
        return new ResponseEntity<>(encryptedUser, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login (@RequestBody LoginRequest loginRequest){
        return new ResponseEntity<>(service.verify(loginRequest), HttpStatus.OK);
    }
}

package com.zack.passwordGenerator.controller;

import com.zack.passwordGenerator.enums.CharacterType;
import com.zack.passwordGenerator.service.PasswordGeneratorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/password")
@CrossOrigin
public class PasswordGeneratorController {

    private final PasswordGeneratorService service;

    public PasswordGeneratorController(PasswordGeneratorService service) {
        this.service = service;
    }

    @GetMapping("/generate")
    public ResponseEntity<String> generatePassword(@RequestParam(defaultValue = "12") int length,
                                                   @RequestParam(required = false) Set<CharacterType> types) {
        String password = service.generate(length, types);
        return new ResponseEntity<>(password, HttpStatus.OK);
    }
}

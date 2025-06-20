package com.zack.passwordGenerator.controller;

import com.zack.passwordGenerator.dto.PasswordDTO;
import com.zack.passwordGenerator.dto.SavePasswordRequest;
import com.zack.passwordGenerator.enums.CharacterType;
import com.zack.passwordGenerator.model.Password;
import com.zack.passwordGenerator.service.PasswordGeneratorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public ResponseEntity<PasswordDTO> generatePassword(@RequestParam(defaultValue = "12") int length,
                                                        @RequestParam(required = false) Set<CharacterType> types) {
        String password = service.generate(length, types);
        PasswordDTO response = new PasswordDTO(password, types, length);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("generate/save")
    public ResponseEntity<String> savePassword(@RequestBody SavePasswordRequest request) {
        return new ResponseEntity<>(service.savePassword(request), HttpStatus.OK);
    }

    @GetMapping("/show")
    public ResponseEntity<List<Password>> getPasswords() {
        return new ResponseEntity<>(service.getPasswords(), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Password>> searchPassword(@RequestParam String keyword){
        return new ResponseEntity<>(service.searchPassword(keyword), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Password> updatePasswordName(@PathVariable int id,
                                                       @RequestParam String newName){
        return new ResponseEntity<>(service.updatePasswordName(id, newName), HttpStatus.OK) ;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePassword(@PathVariable int id){
        return new ResponseEntity<>(service.deletePassword(id), HttpStatus.OK) ;
    }
}

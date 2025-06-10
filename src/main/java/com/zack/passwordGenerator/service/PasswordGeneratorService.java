package com.zack.passwordGenerator.service;

import com.zack.passwordGenerator.enums.CharacterType;
import com.zack.passwordGenerator.model.PasswordGenerator;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PasswordGeneratorService {

    final PasswordGenerator passwordGenerator = new PasswordGenerator();

    public String generate(int length, Set<CharacterType> types) {
        return passwordGenerator.generate(length, types);
    }
}

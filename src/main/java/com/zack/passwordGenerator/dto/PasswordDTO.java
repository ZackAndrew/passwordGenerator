package com.zack.passwordGenerator.dto;

import com.zack.passwordGenerator.enums.CharacterType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class PasswordDTO {
    private String password;
    private Set<CharacterType> types;
    private int length;
}


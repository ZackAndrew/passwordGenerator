package com.zack.passwordGenerator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SavePasswordRequest {
    private PasswordDTO dto;
    private String name;
}

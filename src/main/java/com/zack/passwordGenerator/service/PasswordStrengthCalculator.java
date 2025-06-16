package com.zack.passwordGenerator.service;

import com.zack.passwordGenerator.enums.CharacterType;
import com.zack.passwordGenerator.enums.PasswordStrength;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class PasswordStrengthCalculator {

    public PasswordStrength calculateStrength(int length, Set<CharacterType> types) {
        int score = 0;
        int typeCount = (types == null) ? 0 : types.size();

        if (length >= 12)       score += 3;
        else if (length >= 8)   score += 2;
        else if (length >= 4)   score += 1;

        if (typeCount >= 4)     score += 3;
        else if (typeCount == 3) score += 2;
        else if (typeCount == 2) score += 1;

        return switch (score) {
            case 0,1       -> PasswordStrength.WEAK;
            case 2,3       -> PasswordStrength.MEDIUM;
            case 4         -> PasswordStrength.STRONG;
            default        -> PasswordStrength.VERY_STRONG;
        };

    }
}

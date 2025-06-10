package com.zack.passwordGenerator.model;

import com.zack.passwordGenerator.enums.CharacterType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.SecureRandom;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordGenerator {

    SecureRandom random = new SecureRandom();

    public String generate(int length, Set<CharacterType> types) {
        String allowedChars = types.stream()
                .map(CharacterType::getCharacters)
                .collect(Collectors.joining());

        StringBuilder password = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(allowedChars.length());
            password.append(allowedChars.charAt(index));
        }
        return password.toString();
    }
}

package com.zack.passwordGenerator.service;

import com.zack.passwordGenerator.dto.PasswordDTO;
import com.zack.passwordGenerator.enums.CharacterType;
import com.zack.passwordGenerator.model.Password;
import com.zack.passwordGenerator.model.Users;
import com.zack.passwordGenerator.repo.PasswordRepo;
import com.zack.passwordGenerator.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class PasswordGeneratorService {

    final PasswordGenerator passwordGenerator = new PasswordGenerator();

    final PasswordStrengthCalculator passwordStrengthCalculator = new PasswordStrengthCalculator();

    @Autowired
    PasswordRepo passwordRepo;

    @Autowired
    UserRepo userRepo;

    public String generate(int length, Set<CharacterType> types) {
        return passwordGenerator.generate(length, types);
    }

    public String savePassword(PasswordDTO dto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Users user = userRepo.findByUsername(username);
        if (user == null) throw new RuntimeException("User not found");

        Set<CharacterType> t = Optional.ofNullable(dto.getTypes()).orElse(Set.of());

        Password password = new Password();
        password.setPassword(dto.getPassword());
        password.setUser(user);
        password.setLength(dto.getLength());
        password.setHasUppercase(t.contains(CharacterType.UPPER));
        password.setHasSpecials(t.contains(CharacterType.SYMBOLS));
        password.setHasNumbers(t.contains(CharacterType.DIGITS));
        password.setStrength(passwordStrengthCalculator.calculateStrength(dto.getLength(), t));

        password.setStrength(passwordStrengthCalculator.calculateStrength(dto.getLength(), t));

        passwordRepo.save(password);
        return "Password saved";
    }
}

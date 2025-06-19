package com.zack.passwordGenerator.service;

import com.zack.passwordGenerator.dto.SavePasswordRequest;
import com.zack.passwordGenerator.enums.CharacterType;
import com.zack.passwordGenerator.model.Password;
import com.zack.passwordGenerator.model.Users;
import com.zack.passwordGenerator.repo.PasswordRepo;
import com.zack.passwordGenerator.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public String savePassword(SavePasswordRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Users user = userRepo.findByUsername(username);
        if (user == null) throw new RuntimeException("User not found");

        Set<CharacterType> t = Optional.ofNullable(request.getDto().getTypes()).orElse(Set.of());

        Password password = new Password();
        password.setPassword(request.getDto().getPassword());
        password.setUser(user);
        password.setLength(request.getDto().getLength());
        password.setHasUppercase(t.contains(CharacterType.UPPER));
        password.setHasSpecials(t.contains(CharacterType.SYMBOLS));
        password.setHasNumbers(t.contains(CharacterType.DIGITS));
        password.setStrength(passwordStrengthCalculator.calculateStrength(request.getDto().getLength(), t));

        password.setStrength(passwordStrengthCalculator.calculateStrength(request.getDto().getLength(), t));

        if (request.getName() != null && !request.getName().isBlank()) {
            password.setName(request.getName());
        } else
            password.setName("Untitled");

        passwordRepo.save(password);
        return "Password saved";
    }

    public List<Password> getPasswords() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Users user = userRepo.findByUsername(username);
        if (user == null) throw new RuntimeException("User not found");

        return passwordRepo.findByUser(user);
    }

    public List<Password> searchPassword(String keyword) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Users user = userRepo.findByUsername(username);
        if (user == null) throw new RuntimeException("User not found");

        return passwordRepo.searchPassword(keyword, user);
    }
}

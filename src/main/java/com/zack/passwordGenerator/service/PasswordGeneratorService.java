package com.zack.passwordGenerator.service;

import com.zack.passwordGenerator.dto.SavePasswordRequest;
import com.zack.passwordGenerator.enums.CharacterType;
import com.zack.passwordGenerator.model.Password;
import com.zack.passwordGenerator.model.Users;
import com.zack.passwordGenerator.repo.PasswordRepo;
import com.zack.passwordGenerator.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
        Set<CharacterType> t = Optional.ofNullable(request.getDto().getTypes()).orElse(Set.of());

        Password password = new Password();
        password.setPassword(request.getDto().getPassword());
        password.setUser(currentUser());
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
        return passwordRepo.findByUser(currentUser());
    }

    public List<Password> searchPassword(String keyword) {
        return passwordRepo.searchPassword(keyword, currentUser());
    }

    private Users currentUser() {
        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public Password updatePasswordName(int id, String newName) {
        Users user = currentUser();
        Password password = passwordRepo.findById(id).orElseThrow(() -> new RuntimeException("Password not found"));

        if (currentUser().getUser_id() != password.getUser().getUser_id()) {
            throw new RuntimeException("Access denied");
        }

        password.setName(newName == null || newName.isEmpty() ? "Untitled" : newName.trim());

        return passwordRepo.save(password);
    }

    public String deletePassword(int id) {
        Users user = currentUser();

        Password password = passwordRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("No password"));

        if (password.getUser().getUser_id() != user.getUser_id())
            throw new RuntimeException("Access denied");

        passwordRepo.delete(password);
        return "Deleted";
    }
}

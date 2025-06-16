package com.zack.passwordGenerator.repo;

import com.zack.passwordGenerator.model.Password;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordRepo extends JpaRepository<Password, Integer> {
}

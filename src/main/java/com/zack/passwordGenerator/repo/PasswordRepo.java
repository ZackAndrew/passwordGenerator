package com.zack.passwordGenerator.repo;

import com.zack.passwordGenerator.model.Password;
import com.zack.passwordGenerator.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PasswordRepo extends JpaRepository<Password, Integer> {

    List<Password> findByUser (Users user);
}

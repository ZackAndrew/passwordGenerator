package com.zack.passwordGenerator.repo;

import com.zack.passwordGenerator.model.Password;
import com.zack.passwordGenerator.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PasswordRepo extends JpaRepository<Password, Integer> {

    List<Password> findByUser(Users user);

    @Query("SELECT p FROM Password p WHERE " +
            "LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Password> searchPassword(@Param("keyword") String keyword, @Param("user") Users user);
}

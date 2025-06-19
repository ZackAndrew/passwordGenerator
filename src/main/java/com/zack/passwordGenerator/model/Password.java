package com.zack.passwordGenerator.model;

import com.zack.passwordGenerator.enums.PasswordStrength;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;

@Entity
@Table(name = "passwords")
@Data
public class Password {

    @Id
    @Column(name = "password_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    private String password;

    private Integer length;

    @Enumerated(EnumType.STRING)
    private PasswordStrength strength;

    @Column(name = "has_uppercase")
    private boolean hasUppercase;

    @Column(name = "has_numbers")
    private boolean hasNumbers;

    @Column(name = "has_specials")
    private boolean hasSpecials;

    @Column(nullable = false)
    private String name;

    @CreationTimestamp
    @Column(updatable = false)
    private OffsetDateTime createdAt;
}

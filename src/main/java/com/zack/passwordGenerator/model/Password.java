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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    private String password;

    private int length;

    @Enumerated(EnumType.STRING)
    private PasswordStrength strength;

    @Column(name = "has_uppercase" )
    private boolean hasUppercase;

    @Column(name = "has_numbers" )
    private boolean hasNumbers;

    @Column(name = "has_specials" )
    private boolean hasSpecials;

    @CreationTimestamp
    @Column(updatable = false)
    private OffsetDateTime createdAt;
}

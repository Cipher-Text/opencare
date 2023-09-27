package com.ciphertext.opencarebackend.repository;

import com.ciphertext.opencarebackend.model.entity.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Integer> {
    Optional<PasswordResetToken> findByOtpCode(String otp);
}

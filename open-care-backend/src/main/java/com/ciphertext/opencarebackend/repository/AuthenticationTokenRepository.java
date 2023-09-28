package com.ciphertext.opencarebackend.repository;

import com.ciphertext.opencarebackend.model.entity.AuthenticationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthenticationTokenRepository extends JpaRepository<AuthenticationToken, Integer> {

    Optional<AuthenticationToken> findByToken(String token);
}

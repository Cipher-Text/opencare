package com.ciphertext.opencarebackend.service;

import com.ciphertext.opencarebackend.model.entity.AuthenticationToken;
import com.ciphertext.opencarebackend.model.entity.User;
import com.ciphertext.opencarebackend.repository.AuthenticationTokenRepository;
import com.ciphertext.opencarebackend.repository.UserRepository;
import com.ciphertext.opencarebackend.security.jwt.JWTTokenService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
@RequiredArgsConstructor
public class UserDetailsParser {
    private final HttpServletRequest request;
    private final JWTTokenService tokenService;
    private final UserRepository userRepository;
    private final AuthenticationTokenRepository authenticationTokenRepository;


    public String getCurrentUserName() {
        return tokenService.extractUsername(getToken());
    }

    public int getCurrentUseId() {
        return Integer.parseInt(tokenService.loginUserId(getToken()));
    }

    public String getCurrentUserEmail() {
        return tokenService.extractEmail(getToken());
    }



    public String getToken(){
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String jwt = authorizationHeader.substring(7);
            validateToken(jwt);
            return jwt;
        }
        else {
            throw new AccessDeniedException("No logged in user found!");
        }
    }

    public String getIPAddress(){
        return request.getRemoteAddr();
    }
    private void validateToken(String jwt) {
        AuthenticationToken authToken = authenticationTokenRepository.findByToken(jwt)
                .orElseThrow(() -> new AccessDeniedException("token not found"));
        if(authToken.getTokenExpiredAt().isBefore(LocalDateTime.now())) {
            throw new AccessDeniedException("token expired");
        }
    }

   public User getCurrentUser(){
       return userRepository.findByEmail(getCurrentUserEmail()).orElseThrow(()-> new AccessDeniedException("no logged user found"));
    }

}

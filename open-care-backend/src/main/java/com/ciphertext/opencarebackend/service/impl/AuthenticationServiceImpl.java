package com.ciphertext.opencarebackend.service.impl;

import com.ciphertext.opencarebackend.exception.ResourceNotFoundException;
import com.ciphertext.opencarebackend.model.dto.auth.LoginResponse;
import com.ciphertext.opencarebackend.model.dto.auth.UserLogin;
import com.ciphertext.opencarebackend.model.entity.User;
import com.ciphertext.opencarebackend.repository.UserRepository;
import com.ciphertext.opencarebackend.security.jwt.JWTTokenService;
import com.ciphertext.opencarebackend.service.AuthenticationService;
import com.ciphertext.opencarebackend.service.message.ApplicationMessageResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    @Value("${jwt.token-expire-time}")
    private int tokenExpireTime;
    private final AuthenticationManager authenticationManager;
    private final JWTTokenService jwtTokenService;
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final ApplicationMessageResolver messageResolver;

    @Override
    public LoginResponse login(UserLogin logInRequestDTO) {
        User user = userRepository.findByEmail(logInRequestDTO.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException(messageResolver.getMessage("user.not.found")));
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(logInRequestDTO.getUsername(), logInRequestDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return createTokenResponse(user);
    }
    private LoginResponse createTokenResponse(User user) {
        String token = jwtTokenService.generateToken(user);
        LoginResponse loginResponseDTO = new LoginResponse();
        loginResponseDTO.setToken(token);
        loginResponseDTO.setRefreshToken(jwtTokenService.generateRefreshToken());
        loginResponseDTO.setExpireTime(tokenExpireTime + " minutes");
        return loginResponseDTO;
    }
}

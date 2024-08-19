package com.ciphertext.opencarebackend.service.impl;

import com.ciphertext.opencarebackend.exception.ResourceNotFoundException;
import com.ciphertext.opencarebackend.model.dto.auth.LoginResponse;
import com.ciphertext.opencarebackend.model.dto.auth.ResetPasswordRequestDTO;
import com.ciphertext.opencarebackend.model.dto.auth.UserLogin;
import com.ciphertext.opencarebackend.model.entity.AuthenticationToken;
import com.ciphertext.opencarebackend.model.entity.PasswordResetToken;
import com.ciphertext.opencarebackend.model.entity.User;
import com.ciphertext.opencarebackend.repository.AuthenticationTokenRepository;
import com.ciphertext.opencarebackend.repository.PasswordResetTokenRepository;
import com.ciphertext.opencarebackend.repository.UserRepository;
import com.ciphertext.opencarebackend.security.jwt.JWTTokenService;
import com.ciphertext.opencarebackend.service.AuthenticationService;
import com.ciphertext.opencarebackend.service.event.ForgetPasswordEvent;
import com.ciphertext.opencarebackend.service.event.listener.ForgetPasswordEventListener;
import com.ciphertext.opencarebackend.service.message.ApplicationMessageResolver;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
    private final ForgetPasswordEventListener listener;
    private final HttpServletRequest request;
    private final AuthenticationTokenRepository authenticationTokenRepository;
    private final PasswordResetTokenRepository passwordResetTokenRepository;

    @Override
    public LoginResponse login(UserLogin logInRequestDTO) {
        User user = userRepository.findByEmail(logInRequestDTO.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException(messageResolver.getMessage("user.not.found")));
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(logInRequestDTO.getUsername(), logInRequestDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return createTokenResponse(user);
    }

    @Override
    public void logout(String token) {
        authenticationTokenRepository.findByToken(token)
                .ifPresent(authenticationTokenRepository::delete);
    }

    @Override
    public void forgetPassword(String email) {
        User alumni = userRepository.findByEmail(email)
                .orElseThrow(()-> new ResourceNotFoundException(messageResolver.getMessage("auth.user.not.found")));
        listener.onApplicationEvent(new ForgetPasswordEvent(alumni));
    }

    @Override
    public void resetPassword(ResetPasswordRequestDTO resetPasswordRequestDTO) {
        PasswordResetToken passwordReset = passwordResetTokenRepository.findByOtpCode(resetPasswordRequestDTO.getToken())
                .orElseThrow(()-> new ResourceNotFoundException(messageResolver.getMessage("otp.not.found")));
        User user = passwordReset.getUser();
        user.setPassword(encoder.encode(resetPasswordRequestDTO.getPassword()));
        passwordReset.setChangedStatus(true);
        passwordResetTokenRepository.save(passwordReset);
        userRepository.save(user);
    }

    @Override
    public LoginResponse createRefreshToken(String refreshToken, String jwt) {
        authenticationTokenRepository.findByToken(jwt)
                .ifPresent(authenticationTokenRepository::delete);
        jwtTokenService.validateToken(refreshToken);
        String email = jwtTokenService.extractEmail(jwt);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(messageResolver.getMessage("user.not.found")));
        return createTokenResponse(user);
    }

    private LoginResponse createTokenResponse(User user) {
        String token = jwtTokenService.generateToken(user);
        LoginResponse loginResponseDTO = new LoginResponse();
        loginResponseDTO.setToken(token);
        loginResponseDTO.setRefreshToken(jwtTokenService.generateRefreshToken());
        loginResponseDTO.setExpireTime(tokenExpireTime + " minutes");
        AuthenticationToken authToken = new AuthenticationToken();
        authToken.setToken(token);
        authToken.setIp(request.getRemoteAddr());
        authToken.setTokenCreatedAt(LocalDateTime.now());
        authToken.setTokenExpiredAt(LocalDateTime.now().plusSeconds(tokenExpireTime* 60L));
        authToken.setUser(user);
        authenticationTokenRepository.save(authToken);
        return loginResponseDTO;
    }
}

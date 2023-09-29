package com.ciphertext.opencarebackend.service.event.listener;

import com.ciphertext.opencarebackend.model.entity.PasswordResetToken;
import com.ciphertext.opencarebackend.repository.PasswordResetTokenRepository;
import com.ciphertext.opencarebackend.service.EmailService;
import com.ciphertext.opencarebackend.service.event.ForgetPasswordEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
public class ForgetPasswordEventListener implements ApplicationListener<ForgetPasswordEvent> {
    private String tokenExpireTime= "10";
    private final EmailService emailService;

    private final PasswordResetTokenRepository passwordResetRepository;
    private final SecureRandom secureRandom = new SecureRandom();
    @Override
    public void onApplicationEvent(ForgetPasswordEvent event) {
        String token = String.valueOf(secureRandom.nextInt(900000) +100000);
        PasswordResetToken passwordReset = new PasswordResetToken();
        passwordReset.setUser(event.getUser());
        passwordReset.setOtpCode(token);
        passwordReset.setTokenCreatedAt(LocalDateTime.now());
        passwordReset.setChangedStatus(false);
        passwordResetRepository.save(passwordReset);
        emailService.sentResetPasswordMail(event.getUser(),token, tokenExpireTime);
        log.info("email reset token sent successfully");
    }
}

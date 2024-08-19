package com.ciphertext.opencarebackend.service.event.listener;

import com.ciphertext.opencarebackend.service.EmailService;
import com.ciphertext.opencarebackend.service.event.UserActivationEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserActivationListener implements ApplicationListener<UserActivationEvent> {
    private final EmailService emailService;
    @Override
    public void onApplicationEvent(UserActivationEvent event) {
        emailService.sentUserActivateMail(event.getUser());
        log.info("email sent successfully");
    }
}

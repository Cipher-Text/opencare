package com.ciphertext.opencarebackend.service;

import com.ciphertext.opencarebackend.model.entity.User;
import com.ciphertext.opencarebackend.service.message.ApplicationMessageResolver;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.LocalDateTime;
import java.util.Locale;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;
    @Value("${spring.mail.username}")
    private String fromMail;
    private final ApplicationMessageResolver messageSource;

    @SneakyThrows
    public void sentResetPasswordMail(User user, String token, String tokenExpireTime) {
        try {
            Locale locale = new Locale(Locale.ENGLISH.getDisplayLanguage());
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            message.setSubject(messageSource.getMessage("reset.password.email.subject"));
            message.setFrom(fromMail);
            message.setTo(user.getEmail());

            final Context context = new Context();
            context.setLocale(locale);
            context.setVariable("name", user.getUsername());
            context.setVariable("token", token);
            context.setVariable("expire", LocalDateTime.now().plusMinutes(Long.parseLong(tokenExpireTime)));

            String htmlContent = templateEngine.process("email-template", context);
            message.setText(htmlContent, true);

            javaMailSender.send(message.getMimeMessage());
            log.info("email sent successfully to {}", user.getEmail());
        } catch (MessagingException | MailException e) {
            throw new RuntimeException(e);
        }

    }
}

package com.ciphertext.opencarebackend.service.impl;

import com.ciphertext.opencarebackend.model.entity.Degree;
import com.ciphertext.opencarebackend.model.enums.DegreeType;
import com.ciphertext.opencarebackend.repository.DegreeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceImplTest {

    private PasswordEncoder passwordEncoder;

    public AuthenticationServiceImplTest() {
        passwordEncoder = new BCryptPasswordEncoder();
    }

    @Test
    void generateEncodePassword() {
        System.out.println(passwordEncoder.encode("OpenCare@123"));
    }
}

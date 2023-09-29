package com.ciphertext.opencarebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class OpenCareBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenCareBackendApplication.class, args);
    }

}

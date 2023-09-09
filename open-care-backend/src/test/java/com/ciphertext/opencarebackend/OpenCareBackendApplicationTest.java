package com.ciphertext.opencarebackend;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class OpenCareBackendApplicationTests {

    @Test
    @DisplayName("Should start the application without any exceptions")
    void mainMethodStartsApplicationWithoutExceptions() {
        OpenCareBackendApplication application = new OpenCareBackendApplication();
        assertDoesNotThrow(() -> application.main(new String[]{}));
    }

}
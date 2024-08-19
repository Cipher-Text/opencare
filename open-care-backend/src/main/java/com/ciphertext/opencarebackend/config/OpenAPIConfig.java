package com.ciphertext.opencarebackend.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        type = SecuritySchemeType.HTTP,
        paramName = "Authorization",
        in = SecuritySchemeIn.HEADER,
        name = "bearerAuth",
        bearerFormat = "JWT",
        scheme = "bearer"
)
@OpenAPIDefinition
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("OpenCare API Documentation")
                        .version("1")
                        .description("App Version: 0.0.1")
                        .termsOfService(null)
                        .license(getLicense()));
    }

    private License getLicense() {
        License license = new License();
        license.setUrl("#");
        return license;
    }

}

package com.ciphertext.opencarebackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class MessageConfig implements WebMvcConfigurer {

  private static final String ENCODING = "UTF-8";

  @Bean
  public ResourceBundleMessageSource messageSource() {
    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
    messageSource.setBasenames("i18n/customMessages", "ValidationMessages", "messages");
    messageSource.setUseCodeAsDefaultMessage(true);
    messageSource.setDefaultEncoding(ENCODING);
    messageSource.setCacheSeconds(5);
    return messageSource;
  }

  @Bean
  public LocaleResolver localeResolver() {
    SessionLocaleResolver localeResolver = new SessionLocaleResolver();
    localeResolver.setDefaultLocale(Locale.ENGLISH);
    return localeResolver;
  }

  @Override
  public Validator getValidator() {
    LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
    validator.setValidationMessageSource(messageSource());
    return validator;
  }
}

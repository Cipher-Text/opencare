package com.ciphertext.opencarebackend.annotations;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN', 'DOCTOR','MODERATOR', 'OPERATOR', 'USER')")
public @interface SUPERADMIN {
}

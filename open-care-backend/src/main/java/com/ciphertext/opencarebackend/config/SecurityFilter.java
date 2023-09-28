package com.ciphertext.opencarebackend.config;

import com.ciphertext.opencarebackend.model.entity.AuthenticationToken;
import com.ciphertext.opencarebackend.repository.AuthenticationTokenRepository;
import com.ciphertext.opencarebackend.security.jwt.JWTTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {
    private final JWTTokenService jwtTokenService;
    private final AuthenticationTokenRepository authenticationTokenRepository;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String jwt = authorizationHeader.substring(7);
            validateToken(jwt);
            if(SecurityContextHolder.getContext().getAuthentication() == null){
                List<String> authorities = jwtTokenService.extractAuthorities(jwt);
                List<SimpleGrantedAuthority> simpleGrantedAuthorities = authorities.stream().map(SimpleGrantedAuthority::new).toList();

                if(jwtTokenService.validateToken(jwt)){
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(null, null, simpleGrantedAuthorities);
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        }
        filterChain.doFilter(request, response);
    }

    private void validateToken(String jwt) {
        AuthenticationToken authToken = authenticationTokenRepository.findByToken(jwt)
                .orElseThrow(() -> new AccessDeniedException("token not found"));
        if(authToken.getTokenExpiredAt().isBefore(LocalDateTime.now())) {
            throw new AccessDeniedException("token expired");
        }
    }

}

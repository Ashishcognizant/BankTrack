package com.cts.security;

import com.cts.model.User;
import com.cts.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class AuthBeansConfig {

    private final UserRepository userRepository;

    /**
     * Expose a UserDetailsService so Spring Security does NOT create the default 'user/<generated>'
     * and so you can still leverage method security annotations if needed.
     */
    @Bean
    public UserDetailsService userDetailsService() {
        // Use email as the username
        return (String username) -> {
            User u = userRepository.findByEmail(username.toLowerCase())
                    .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

            // NOTE:
            // For JWT-only PoC (no password in entity), we set a dummy password with {noop}.
            // If you later add a passwordHash, return it here with "{bcrypt}<hash>" and add a DaoAuthenticationProvider.
            return org.springframework.security.core.userdetails.User
                    .withUsername(u.getEmail())
                    .password("{noop}N/A")                 // JWT-only PoC without passwords
                    .roles(u.getRole().name())             // CUSTOMER/OFFICER/ADMIN (adds ROLE_ prefix)
                    .accountLocked(false)
                    .disabled(false)
                    .build();
        };
    }

    /**
     * Keep BCrypt ready if/when you add local password-based login later.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}

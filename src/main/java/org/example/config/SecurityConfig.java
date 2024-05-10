package org.example.library.config;

import org.example.library.security.JwtTokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtTokenFilter filter;

    public SecurityConfig(JwtTokenFilter filter) {
        this.filter = filter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(final AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(final HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(configurer ->
                        configurer.requestMatchers(
                                        "/swagger-ui/**",
                                        "/v3/api-docs/**",
                                        "/users/get/{id}",
                                        "/users/find/username/{username}", // Corrected URL pattern
                                        "/books/books",
                                        "/books/create",
                                        "/api/v1/auth/register",
                                        "/api/v1/auth/login",
                                        "/books/get/{{id}}",
                                        "/users/**",
                                        "/api/v4/action/create",
                                        "/api/v4/UserAction/create",
                                        "/api/v4/UserAction/get/{{userId}}",
                                        "/books/delete/{{id}}",
                                        "/books/action/{{userId}}",
                                        "/books/books/likes/**",
                                        "/books/books/category/**")
                                .permitAll()
                                .anyRequest()
                                .authenticated())
                .anonymous(AbstractHttpConfigurer::disable)
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
}

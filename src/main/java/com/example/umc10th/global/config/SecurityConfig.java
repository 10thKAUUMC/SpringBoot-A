package com.example.umc10th.global.config;

import com.example.umc10th.global.security.handler.SecurityExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final SecurityExceptionHandler securityExceptionHandler;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        //Uses Bcrypt hash function to encrypt the password(including salting)
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) //Since it's an API server, disabling CSRF
                .authorizeHttpRequests(auth -> auth
                        //Public API: permit register & logic etc.
                        .requestMatchers("/api/users/join", "/api/users/login").permitAll() //Can use it without register
                        //Open up Swagger as well
                        .requestMatchers("/v3/api-doc/**", "/swagger-ui/**").permitAll()
                        //Other than these two, requires authentication
                        .anyRequest().authenticated()
                )
                //Authorization/Authenication exceptions
                .exceptionHandling(handler -> handler
                        .authenticationEntryPoint(securityExceptionHandler) //Error 401
                        .accessDeniedHandler(securityExceptionHandler) //Error 403
                );

        return http.build();
    }

}

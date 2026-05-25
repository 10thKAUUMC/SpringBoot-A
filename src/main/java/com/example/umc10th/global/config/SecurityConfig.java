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

    private final String[] allowUris = {
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/v3/api-docs/**",
            "/auth/**"
    };


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
                        .requestMatchers(allowUris).permitAll()
                        //Other than these two, requires authentication
                        .anyRequest().authenticated() //if you wanna see it then use permitAll()
                )
                .formLogin(form -> form
                        .defaultSuccessUrl("/swagger-ui/index.html", true)
                        .permitAll()
                )
                //Authorization/Authenication exceptions
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(securityExceptionHandler) //Error 401
                        .accessDeniedHandler(securityExceptionHandler) //Error 403
                );

        return http.build();
    }
}

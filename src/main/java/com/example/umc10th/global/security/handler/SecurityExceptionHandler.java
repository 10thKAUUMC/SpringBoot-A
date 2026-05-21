package com.example.umc10th.global.security.handler;

import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class SecurityExceptionHandler implements AuthenticationEntryPoint, AccessDeniedHandler {

    // Authorization failed (Not logged in) - 401 Unauthorized
    @Override
    public void commence(jakarta.servlet.http.HttpServletRequest request, HttpServletResponse response, @Nullable AuthenticationException authException) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        //Temporarily writing this, I might change it later on
        response.getWriter().write("{\"isSuccess\": false, \"code\":\"COMMON401\", \"message\": \"인증이 필요합니다.\"}");

    }

    //Authentication failed (No access) - 403 Forbidden
    @Override
    public void handle(jakarta.servlet.http.HttpServletRequest request, HttpServletResponse response, @Nullable AccessDeniedException accessDeniedException) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.getWriter().write("{\"isSuccess\": false, \"code\":\"COMMON403\", \"message\": \"접근 권한이 없습니다.\"}");
    }

}

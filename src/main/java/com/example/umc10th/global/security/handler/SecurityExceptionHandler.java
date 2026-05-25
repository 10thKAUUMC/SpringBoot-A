package com.example.umc10th.global.security.handler;

import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.GeneralErrorCode;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

@Component
public class SecurityExceptionHandler implements AuthenticationEntryPoint, AccessDeniedHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    // Authorization failed (Not logged in) - 401 Unauthorized (AuthenticationEntryPoint)
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         @Nullable AuthenticationException authException) throws IOException {
        GeneralErrorCode errorCode = GeneralErrorCode.UNAUTHORIZED;
        setResponse(response, errorCode);
    }

    //Authentication failed (No access) - 403 Forbidden (AccessDeniedHandler)
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       @Nullable AccessDeniedException accessDeniedException) throws IOException {
        GeneralErrorCode errorCode = GeneralErrorCode.FORBIDDEN;
        setResponse(response, errorCode);
    }

    private void setResponse(HttpServletResponse response, GeneralErrorCode errorCode) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(errorCode.getStatus().value());

        ApiResponse<Object> apiResponse = ApiResponse.onFailure(errorCode.getCode(), errorCode.getMessage(), null);
        response.getWriter().write(objectMapper.writeValueAsString(apiResponse));
    }

}

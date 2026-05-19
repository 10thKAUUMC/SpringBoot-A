package com.example.umc10th.global.config.security;

import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public final class SecurityErrorResponseWriter {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private SecurityErrorResponseWriter() {
    }

    public static void write(HttpServletResponse response, BaseErrorCode code) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(code.getStatus().value());
        ApiResponse<Void> errorResponse = ApiResponse.onFailure(code, null);
        OBJECT_MAPPER.writeValue(response.getOutputStream(), errorResponse);
    }
}

package com.example.umc10th.global.security.handler.filter; // 패키지 경로 확인하세요!

import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc10th.global.security.handler.util.JwtUtil;
import com.example.umc10th.global.security.handler.service.CustomUserDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import jakarta.annotation.Nonnull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;
    private final ObjectMapper objectMapper = new ObjectMapper(); //Set it to field for reuse

    @Override
    protected void doFilterInternal(
            @Nonnull HttpServletRequest request,
            @Nonnull HttpServletResponse response,
            @Nonnull FilterChain filterChain
    ) throws ServletException, IOException {

        try {
            String authorization = request.getHeader("Authorization");

            // 1. Pass if the token not exists or not fit the format (Next filter checks Authentication)
            if (authorization == null || !authorization.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }

            // 2. Remove prefix Bearer
            String token = authorization.substring(7);

            // 3. Process Authentication and verify token
            if (jwtUtil.isValid(token)) {
                String email = jwtUtil.getEmail(token);

                // Loads user's information
                UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);

                //Creates authentication object in spring security
                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                //Saves Authentication information in ContextHolder
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

            filterChain.doFilter(request, response);

        } catch (Exception e) {
            // Respond to exceptions occurring in the filter stage according to our project specification(ApiResponse).
            handleException(response, GeneralErrorCode.UNAUTHORIZED);
        }
    }

    private void handleException(HttpServletResponse response, GeneralErrorCode errorCode) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(errorCode.getStatus().value());

        // Apply common response specification
        ApiResponse<Object> errorResponse = ApiResponse.onFailure(errorCode.getCode(), errorCode.getMessage(), null);

        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}
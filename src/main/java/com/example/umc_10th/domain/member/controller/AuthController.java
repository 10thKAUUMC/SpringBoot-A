package com.example.umc_10th.domain.member.controller;

import com.example.umc_10th.domain.member.dto.MemberReqDTO;
import com.example.umc_10th.domain.member.dto.MemberResDTO;
import com.example.umc_10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc_10th.domain.member.service.AuthService;
import com.example.umc_10th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    // 회원가입
    @PostMapping("/v1/signup")
    public ResponseEntity<ApiResponse<MemberResDTO.Signup>> signup(
            @Valid @RequestBody MemberReqDTO.Signup req
    ) {
        MemberResDTO.Signup response = authService.signup(req);
        return ResponseEntity.ok(
                ApiResponse.onSuccess(MemberSuccessCode.SIGNUP_OK, response)
        );
    }


    // 로그인
    @PostMapping("/v1/login")
    public ResponseEntity<ApiResponse<MemberResDTO.Login>> login(
            @Valid @RequestBody MemberReqDTO.Login req
    ) {
        MemberResDTO.Login response = authService.login(req);
        return ResponseEntity.ok(
                ApiResponse.onSuccess(MemberSuccessCode.LOGIN_OK, response)
        );
    }
}

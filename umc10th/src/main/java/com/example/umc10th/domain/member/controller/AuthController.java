package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.req.MemberReqDTO;
import com.example.umc10th.domain.member.dto.res.MemberResDTO;
import com.example.umc10th.domain.member.exeption.code.MemberSuccessCode;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "인증", description = "회원가입 등")
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final MemberService memberService;

    @Operation(
            summary = "회원가입",
            description = "이름·이메일·선호 음식 카테고리 등을 등록하여 회원을 생성합니다."
    )
    @PostMapping("/signup")
    public ApiResponse<MemberResDTO.Signup> signup(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "회원가입 요청")
            @Valid @RequestBody MemberReqDTO.Signup request
    ) {
        MemberResDTO.Signup result = memberService.signup(request);
        BaseSuccessCode code = MemberSuccessCode.SIGNUP_OK;
        return ApiResponse.onSuccess(code, result);
    }
}

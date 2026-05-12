package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.req.MemberReqDTO;
import com.example.umc10th.domain.member.dto.res.MemberResDTO;
import com.example.umc10th.domain.member.exeption.code.MemberSuccessCode;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ApiResponse<MemberResDTO.Signup> signup(
            @Valid @RequestBody MemberReqDTO.Signup request
    ) {
        MemberResDTO.Signup result = memberService.signup(request);
        BaseSuccessCode code = MemberSuccessCode.SIGNUP_OK;
        return ApiResponse.onSuccess(code, result);
    }
}

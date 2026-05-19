package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.res.MemberResDTO;
import com.example.umc10th.domain.member.exeption.code.MemberSuccessCode;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.domain.member.security.AuthMember;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "회원", description = "마이페이지 등 회원 정보")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class MemberController {

    private final MemberService memberService;

    @Operation(
            summary = "마이페이지(v2) — 내 정보 조회",
            description = "Authorization 헤더의 Bearer JWT에서 인증된 회원 정보를 조회합니다."
    )
    @GetMapping("/me")
    public ApiResponse<MemberResDTO.GetInfo> getMyInfo(
            @AuthenticationPrincipal AuthMember authMember
    ) {
        BaseSuccessCode code = MemberSuccessCode.OK;
        return ApiResponse.onSuccess(code, memberService.getMyInfo(authMember.getMember()));
    }
}

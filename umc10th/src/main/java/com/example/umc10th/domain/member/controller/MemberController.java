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

@Tag(name = "회원", description = "마이페이지 등 회원 정보")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    @Operation(
            summary = "마이페이지 — 회원 정보 조회",
            description = "요청 본문의 회원 `id`로 프로필·포인트 등 기본 정보를 조회합니다."
    )
    @PostMapping("/v1/users/me")
    public ApiResponse<MemberResDTO.GetInfo> getInfo(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "조회할 회원 PK(`id`)")
            @Valid @RequestBody MemberReqDTO.GetInfo dto
    ) {
        BaseSuccessCode code = MemberSuccessCode.OK;
        return ApiResponse.onSuccess(code, memberService.getInfo(dto));
    }
}

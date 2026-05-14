package com.example.umc_10th.domain.member.controller;

import com.example.umc_10th.domain.member.dto.MemberReqDTO;
import com.example.umc_10th.domain.member.dto.MemberResDTO;
import com.example.umc_10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc_10th.domain.member.service.MemberService;
import com.example.umc_10th.global.apiPayload.ApiResponse;
import com.example.umc_10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {
    private final MemberService memberService;

    // 마이페이지 API
    @PostMapping("/v1/users/me")
    public ApiResponse<MemberResDTO.GetInfo> getInfo(
            @RequestBody MemberReqDTO.GetInfo dto
    ) {
        BaseSuccessCode code = MemberSuccessCode.OK;
        return ApiResponse.onSuccess(code, memberService.getInfo(dto));
    }

    // 사용자 홈 화면 API
    @GetMapping("/v1/users/home")
    public ResponseEntity<ApiResponse<MemberResDTO.Home>> getHome() {

        Long memberId = 1L; // 임시 사용자 ID

        MemberResDTO.Home response =
                memberService.getHomeData(memberId);

        return ResponseEntity.ok(
                ApiResponse.onSuccess(MemberSuccessCode.OK, response)
        );
    }

}

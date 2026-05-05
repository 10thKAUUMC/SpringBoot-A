package com.example.umc_10th.domain.member.controller;

import com.example.umc_10th.domain.member.dto.MemberReqDTO;
import com.example.umc_10th.domain.member.dto.MemberResDTO;
import com.example.umc_10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc_10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc_10th.domain.member.service.MemberService;
import com.example.umc_10th.global.apiPayload.ApiResponse;
import com.example.umc_10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/v1/users/me")
    public ApiResponse<MemberResDTO.GetInfo> getInfo(
            @RequestBody MemberReqDTO.GetInfo dto
    ) {
        BaseSuccessCode code = MemberSuccessCode.OK;
        return ApiResponse.onSuccess(code, memberService.getInfo(dto));
    }
    // 사용자 지역 조회
    @GetMapping("/v1/users/location")
    public ResponseEntity<ApiResponse<MemberResDTO.GetLocation>> getUserLocations() {

        MemberResDTO.GetLocation location =
                MemberResDTO.GetLocation.builder()
                        .location("서울특별시 강남구")
                        .build();

        return ResponseEntity.ok(ApiResponse.onSuccess(MemberSuccessCode.OK, location));
    }

    // 사용자 포인트 조회
    @GetMapping("/v1/users/point")
    public ResponseEntity<ApiResponse<MemberResDTO.GetPoint>> getUserPoints() {

        MemberResDTO.GetPoint point =
                MemberResDTO.GetPoint.builder()
                        .point(120)
                        .build();

        return ResponseEntity.ok(ApiResponse.onSuccess(MemberSuccessCode.OK, point));
    }


}

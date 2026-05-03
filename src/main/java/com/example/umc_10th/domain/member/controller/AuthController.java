package com.example.umc_10th.domain.member.controller;

import com.example.umc_10th.domain.member.dto.MemberReqDTO;
import com.example.umc_10th.domain.member.dto.MemberResDTO;
import com.example.umc_10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc_10th.domain.member.repository.MemberRepository;
import com.example.umc_10th.global.apiPayload.ApiResponse;
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
    private final MemberRepository memberRepository;

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<MemberResDTO.Signup>> signup(
            @RequestBody MemberReqDTO.Signup req
    ) {

        //더미 ID 생성
        Long dummyMemberId = 1L;

        MemberResDTO.Signup response = MemberResDTO.Signup.builder()
                .memberId(dummyMemberId)
                .name(req.name())
                .build();

        return ResponseEntity.ok(
                ApiResponse.onSuccess(MemberSuccessCode.SIGNUP_OK, response)
        );
    }

}

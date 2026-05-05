package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.req.MemberReqDTO;
import com.example.umc10th.domain.member.dto.res.MemberResDTO;
import com.example.umc10th.domain.member.exeption.code.MemberSuccessCode;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/signup")
    public ApiResponse<MemberResDTO.Signup> signup(
            @RequestBody MemberReqDTO.Signup request
    ) {
        MemberResDTO.Signup result = MemberResDTO.Signup.builder()
                .userName(request.userName())
                .gender(request.gender())
                .birthday(request.birthday())
                .address(request.address())
                .detailAddress(request.detailAddress())
                .email(request.email())
                .phone(request.phone())
                .category(request.category())
                .message("회원가입에 성공했습니다.")
                .build();

        BaseSuccessCode code = MemberSuccessCode.SIGNUP_OK;
        return ApiResponse.onSuccess(code, result);
    }
}

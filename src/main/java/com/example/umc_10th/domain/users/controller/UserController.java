package com.example.umc_10th.domain.users.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.umc_10th.domain.users.service.UserService;

import com.example.umc_10th.global.apiPayload.ApiResponse;
import com.example.umc_10th.domain.users.dto.UserReqDTO;
import com.example.umc_10th.domain.users.dto.UserResDTO;

// POST/auth/users -> 회원가입 전용 Controller입니다 !

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    public ApiResponse<UserResDTO.JoinResultDTO> join(
            @RequestBody UserReqDTO.JoinDTO request
    ) {
        return ApiResponse.onSuccess(
                userService.join(request)
        );
    }


}

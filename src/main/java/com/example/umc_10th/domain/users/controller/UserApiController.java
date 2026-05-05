package com.example.umc_10th.domain.users.controller;

import com.example.umc_10th.domain.users.dto.UserResDTO;
import com.example.umc_10th.domain.users.service.UserService;
import com.example.umc_10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserApiController {

    private final UserService userService;

    @GetMapping("/users/me")
    public ApiResponse<UserResDTO.MyPageDTO> getMyPage() {
        return ApiResponse.onSuccess(
                userService.getMyPage()
        );
    }
}

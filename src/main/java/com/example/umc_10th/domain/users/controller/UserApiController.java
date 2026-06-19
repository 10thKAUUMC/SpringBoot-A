package com.example.umc_10th.domain.users.controller;

import com.example.umc_10th.domain.users.dto.UserReqDTO;
import com.example.umc_10th.domain.users.dto.UserResDTO;
import com.example.umc_10th.domain.users.service.UserService;
import com.example.umc_10th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserApiController {

    private final UserService userService;

    @PostMapping("/users/join")
    public ApiResponse<UserResDTO.JoinResultDTO> join(
            @RequestBody @Valid UserReqDTO.JoinDTO request
    ) {
        return ApiResponse.onSuccess(
                userService.join(request)
        );
    }

    @GetMapping("/users/me")
    public ApiResponse<UserResDTO.MyPageDTO> getMyPage() {
        return ApiResponse.onSuccess(
                userService.getMyPage()
        );
    }
}

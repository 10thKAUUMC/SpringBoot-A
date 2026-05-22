package com.example.umc_10th.domain.users.controller;

import com.example.umc_10th.domain.users.dto.UserReqDTO;
import com.example.umc_10th.domain.users.dto.UserResDTO;
import com.example.umc_10th.domain.users.service.HomeService;
import com.example.umc_10th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class HomeController {

    private final HomeService homeService;

    @PostMapping("/home")
    public ApiResponse<UserResDTO.HomeDTO> getHome(
            @RequestBody @Valid UserReqDTO.HomeRequestDTO request,
            @RequestParam Long regionId,
            @RequestParam(defaultValue = "0") Integer page
    ) {
        return ApiResponse.onSuccess(
                homeService.getHome(request.memberId(), regionId, page)
        );
    }
}

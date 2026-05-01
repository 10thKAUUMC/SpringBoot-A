package com.example.umc_10th.domain.users.controller;
import com.example.umc_10th.domain.users.dto.UserResDTO;
import com.example.umc_10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
// GET/home -> 홈 화면 출력 전용 Controller 입니다 !
@RestController
@RequiredArgsConstructor
public class HomeController {

    @GetMapping("/home")
    public ApiResponse<UserResDTO.HomeResultDTO> getHome() {
        return ApiResponse.onSuccess(
                UserResDTO.HomeResultDTO.builder()
                        .message("홈 화면 데이터")
                        .build()
        );
    }
}

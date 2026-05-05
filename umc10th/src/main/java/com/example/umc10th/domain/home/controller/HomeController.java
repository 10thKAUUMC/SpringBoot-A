package com.example.umc10th.domain.home.controller;

import com.example.umc10th.domain.home.dto.res.HomeResDTO;
import com.example.umc10th.domain.home.exception.code.HomeSuccessCode;
import com.example.umc10th.domain.home.service.HomeService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/home")
public class HomeController {

    private final HomeService homeService;

    @GetMapping("/my-missions")
    public ApiResponse<HomeResDTO.MyMissions> getMyMissions(
            @RequestParam(name = "is_complete", required = false, defaultValue = "false") boolean isComplete,
            @RequestParam(name = "member_id", required = false, defaultValue = "1") Long memberId,
            @RequestParam(name = "address", required = false, defaultValue = "안암동") String address,
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false) Integer size
    ) {
        HomeResDTO.MyMissions result = homeService.getMyMissions(
                memberId,
                address,
                isComplete,
                page,
                size
        );
        BaseSuccessCode code = HomeSuccessCode.HOME_MISSION_LIST_OK;
        return ApiResponse.onSuccess(code, result);
    }
}

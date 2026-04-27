package com.example.umc10th.domain.home.controller;

import com.example.umc10th.domain.home.dto.res.HomeResDTO;
import com.example.umc10th.domain.home.exception.code.HomeSuccessCode;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {

    @GetMapping("/my-missions")
    public ApiResponse<HomeResDTO.MyMissions> getMyMissions(
            @RequestParam(name = "is_complete", required = false) Integer isComplete
    ) {
        List<HomeResDTO.MissionSummary> missions = List.of(
                HomeResDTO.MissionSummary.builder()
                        .storeId(1L)
                        .storeName("반이학생마라탕")
                        .storeCategory("중식당")
                        .dDay(7)
                        .minPrice(10000)
                        .accumulatePoint(500)
                        .build(),
                HomeResDTO.MissionSummary.builder()
                        .storeId(2L)
                        .storeName("가게이름a")
                        .storeCategory("일식당")
                        .dDay(7)
                        .minPrice(12000)
                        .accumulatePoint(500)
                        .build()
        );

        HomeResDTO.MyMissions result = HomeResDTO.MyMissions.builder()
                .address("안암동")
                .myPoint(999999)
                .progressMission(7)
                .targetMission(10)
                .progressPoint(1000)
                .missions(missions)
                .build();

        BaseSuccessCode code = HomeSuccessCode.HOME_MISSION_LIST_OK;
        return ApiResponse.onSuccess(code, result);
    }
}

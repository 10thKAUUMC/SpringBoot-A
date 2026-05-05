package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.res.MissionResDTO;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/missions")
public class MissionController {

    @GetMapping("/mission-list")
    public ApiResponse<MissionResDTO.MissionList> getMissionList(
            @RequestParam(name = "is_complete") Integer isComplete
    ) {
        List<MissionResDTO.MissionSummary> missions = List.of(
                MissionResDTO.MissionSummary.builder()
                        .storeId(1L)
                        .storeName("반이학생마라탕")
                        .minPrice(10000)
                        .pointPercent(5)
                        .isComplete(isComplete)
                        .build(),
                MissionResDTO.MissionSummary.builder()
                        .storeId(2L)
                        .storeName("가게이름a")
                        .minPrice(12000)
                        .pointPercent(5)
                        .isComplete(isComplete)
                        .build()
        );

        MissionResDTO.MissionList result = MissionResDTO.MissionList.builder()
                .missions(missions)
                .build();

        BaseSuccessCode code = MissionSuccessCode.MISSION_LIST_OK;
        return ApiResponse.onSuccess(code, result);
    }

    @PatchMapping("/{missionId}/complete")
    public ApiResponse<MissionResDTO.MissionComplete> completeMission(
            @PathVariable Long missionId
    ) {
        MissionResDTO.MissionComplete result = MissionResDTO.MissionComplete.builder()
                .isComplete(1)
                .message("미션이 성공되었습니다.")
                .build();

        BaseSuccessCode code = MissionSuccessCode.MISSION_COMPLETE_OK;
        return ApiResponse.onSuccess(code, result);
    }
}

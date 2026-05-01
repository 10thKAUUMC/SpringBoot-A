package com.example.umc_10th.domain.mission.controller;

import com.example.umc_10th.domain.mission.dto.MissionResDTO;
import com.example.umc_10th.domain.mission.enums.MissionStatus;
import com.example.umc_10th.domain.mission.service.MissionService;
import com.example.umc_10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.umc_10th.domain.mission.dto.MissionReqDTO;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MissionController {

    private final MissionService missionService;
    // 미션 목록 조회(진행중, 진행 완료) 전용 Controller 입니다 !
    @GetMapping("/missions")
    public ApiResponse<MissionResDTO.MissionListDTO> getMissions(
            @RequestParam MissionStatus status
    ) {
        return ApiResponse.onSuccess(
                missionService.getMissions(status)
        );
    }
    // 미션 성공 누르기 전용 Controller 입니다 !
    @PatchMapping("/missions/{missionId}/success")
    public ApiResponse<MissionResDTO.SuccessResultDTO> successMission(
            @PathVariable Long missionId,
            @RequestBody MissionReqDTO.SuccessDTO request
    ) {
        return ApiResponse.onSuccess(
                missionService.successMission(missionId, request)
        );
    }
}

package com.example.umc_10th.domain.mission.controller;

import com.example.umc_10th.domain.mission.dto.MissionReqDTO;
import com.example.umc_10th.domain.mission.dto.MissionResDTO;
import com.example.umc_10th.domain.mission.enums.MissionStatus;
import com.example.umc_10th.domain.mission.service.MissionService;
import com.example.umc_10th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MissionController {

    private final MissionService missionService;

    // 미션 목록 조회(진행중, 진행 완료) 전용 Controller 입니다 !
    @PostMapping("/missions")
    public ApiResponse<MissionResDTO.MissionListDTO> getMissions(
            @RequestBody @Valid MissionReqDTO.MyMissionDTO request,
            @RequestParam MissionStatus status,
            @RequestParam(defaultValue = "0") Integer page
    ) {
        return ApiResponse.onSuccess(
                missionService.getMissions(request.memberId(), status, page)
        );
    }

    // 7주차 미션 추가 : 내가 진행 중인 미션 조회
    @PostMapping("/missions/progress")
    public ApiResponse<MissionResDTO.MissionListDTO> getMyProgressMissions(
            @RequestBody @Valid MissionReqDTO.MyMissionDTO request,
            @RequestParam(defaultValue = "0") Integer page
    ) {
        return ApiResponse.onSuccess(
                missionService.getMyProgressMissions(request.memberId(), page)
        );
    }

    // 미션 성공 누르기 전용 Controller 입니다 !
    @PatchMapping("/missions/{missionId}/success")
    public ApiResponse<MissionResDTO.SuccessResultDTO> successMission(
            @PathVariable Long missionId
    ) {
        return ApiResponse.onSuccess(
                missionService.successMission(missionId)
        );
    }
}

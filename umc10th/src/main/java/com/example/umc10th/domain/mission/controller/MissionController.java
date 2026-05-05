package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.res.MissionResDTO;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionController {

    private final MissionService missionService;

    @GetMapping("/mission-list")
    public ApiResponse<MissionResDTO.MissionList> getMissionList(
            @RequestParam(name = "is_complete") int isComplete,
            @RequestParam(name = "member_id", required = false, defaultValue = "1") Long memberId,
            @RequestParam(required = false) String cursor,
            @RequestParam(required = false) Integer size
    ) {
        MissionResDTO.MissionList result = missionService.getMissionList(memberId, isComplete, cursor, size);
        BaseSuccessCode code = MissionSuccessCode.MISSION_LIST_OK;
        return ApiResponse.onSuccess(code, result);
    }

    @PatchMapping("/{missionId}/complete")
    public ApiResponse<MissionResDTO.MissionComplete> completeMission(
            @PathVariable Long missionId,
            @RequestParam(name = "member_id", required = false, defaultValue = "1") Long memberId
    ) {
        MissionResDTO.MissionComplete result = missionService.completeMission(memberId, missionId);
        BaseSuccessCode code = MissionSuccessCode.MISSION_COMPLETE_OK;
        return ApiResponse.onSuccess(code, result);
    }
}

package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.req.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.res.MissionResDTO;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stores")
public class StoreMissionController {

    private final MissionService missionService;

    @PostMapping("/{storeId}/missions")
    public ApiResponse<Void> createMission(
            @PathVariable Long storeId,
            @RequestBody MissionReqDTO.CreateMission request
    ) {
        missionService.createStoreMission(storeId, request);
        BaseSuccessCode code = MissionSuccessCode.STORE_MISSION_CREATED;
        return ApiResponse.onSuccess(code, null);
    }

    @GetMapping("/{storeId}/missions")
    public ApiResponse<MissionResDTO.StoreMissionPage> getMissions(
            @PathVariable Long storeId,
            @RequestParam(name = "pageNumber", required = false, defaultValue = "1") int pageNumber,
            @RequestParam(name = "pageSize", required = false) Integer pageSize
    ) {
        MissionResDTO.StoreMissionPage result = missionService.getStoreMissions(storeId, pageNumber, pageSize);
        BaseSuccessCode code = MissionSuccessCode.STORE_MISSION_LIST_OK;
        return ApiResponse.onSuccess(code, result);
    }
}

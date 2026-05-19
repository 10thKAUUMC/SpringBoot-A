package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.req.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.res.MissionResDTO;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "가게 미션", description = "가게 단위 미션 등록 및 가게에 속한 미션 목록(페이징)")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stores")
public class StoreMissionController {

    private final MissionService missionService;

    @Operation(
            summary = "가게 미션 생성",
            description = "워크북 스펙에 맞춰 가게에 새 미션(마감일·포인트·조건 등)을 등록합니다."
    )
    @PostMapping("/{storeId}/missions")
    public ApiResponse<Void> createMission(
            @Parameter(description = "가게 PK", required = true, example = "1")
            @PathVariable Long storeId,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "미션 생성 요청 본문")
            @Valid @RequestBody MissionReqDTO.CreateMission request
    ) {
        missionService.createStoreMission(storeId, request);
        BaseSuccessCode code = MissionSuccessCode.STORE_MISSION_CREATED;
        return ApiResponse.onSuccess(code, null);
    }

    @Operation(
            summary = "가게 미션 목록 조회",
            description = "해당 가게에 등록된 미션을 **오프셋 페이지**로 조회합니다(미션 ID 내림차순)."
    )
    @GetMapping("/{storeId}/missions")
    public ApiResponse<MissionResDTO.StoreMissionPage> getMissions(
            @Parameter(description = "가게 PK", required = true, example = "1")
            @PathVariable Long storeId,
            @Parameter(description = "페이지 번호(1부터)", example = "1")
            @RequestParam(name = "pageNumber", required = false, defaultValue = "1") int pageNumber,
            @Parameter(description = "페이지 크기(미지정 시 서버 기본값)")
            @RequestParam(name = "pageSize", required = false) Integer pageSize
    ) {
        MissionResDTO.StoreMissionPage result = missionService.getStoreMissions(storeId, pageNumber, pageSize);
        BaseSuccessCode code = MissionSuccessCode.STORE_MISSION_LIST_OK;
        return ApiResponse.onSuccess(code, result);
    }
}

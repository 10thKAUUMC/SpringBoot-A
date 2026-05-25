package com.example.umc10th.domain.mission.api;

import com.example.umc10th.domain.mission.domain.UserMission;
import com.example.umc10th.domain.mission.dto.MissionResponseDTO;
import com.example.umc10th.domain.mission.service.MissionCommandService;
import com.example.umc10th.domain.mission.service.UserMissionQueryService;
import com.example.umc10th.domain.user.converter.UserMissionConverter;
import com.example.umc10th.domain.user.dto.UserResponseDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class MissionRestController {

    private final UserMissionQueryService missionQueryService;
    private final MissionCommandService missionCommandService;


    @GetMapping("/missions")
    @Operation(summary = "가게 미션 목록 조회 API", description = "특정 가게의 미션 목록을 조회합니다.")
    public ApiResponse<UserResponseDTO.UserMissionPreViewListDTO> getMissionList(@RequestParam(name = "storeId") Long storeId, @RequestParam(name = "page") Integer page) {
        Page<UserMission> missionPage = missionQueryService.getMissionsByStore(storeId, page);

        return ApiResponse.onSuccess(UserMissionConverter.userMissionPreViewListDTO(missionPage));

    }

    @PostMapping("/{mission_id}/complete")
    @Operation(summary = "미션 완료 API", description = "특정 미션을 완료 상태로 변경합니다.")
    public ApiResponse<MissionResponseDTO.MissionCompleteResultDTO> completeMission(@PathVariable(name = "mission_id") Long missionId) {
        return ApiResponse.onSuccess(missionCommandService.complete(missionId));
    }
}
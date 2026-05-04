package com.example.umc10th.domain.mission.api;

import com.example.umc10th.domain.mission.dto.MissionResponseDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/stores")
public class MissionRestController {

    @GetMapping("/missions")
    @Operation(summary = "가게 미션 목록 조회 API", description = "특정 가게의 미션 목록을 조회합니다.")
    public ApiResponse<MissionResponseDTO.MissionViewListDTO> getMissionList(@RequestParam(name = "storeId") Long storeId) {

        MissionResponseDTO.MissionViewListDTO result = MissionResponseDTO.MissionViewListDTO.builder()
                .missionList(new ArrayList<>())
                .build();

        return ApiResponse.onSuccess(result);
    }

    @PostMapping("/{mission_id}/complete")
    @Operation(summary = "미션 완료 API", description = "특정 미션을 완료 상태로 변경합니다.")
    public ApiResponse<MissionResponseDTO.MissionCompleteResultDTO> completeMission(@PathVariable(name = "mission_id") Long missionId) {
        // Returns dummy data for now
        MissionResponseDTO.MissionCompleteResultDTO result = MissionResponseDTO.MissionCompleteResultDTO.builder()
                .missionId(missionId)
                .completedAt(LocalDateTime.now())
                .build();

        return ApiResponse.onSuccess(result);
    }
}
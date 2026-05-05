package com.example.umc_10th.domain.mission.controller;

import com.example.umc_10th.domain.mission.dto.MissionResDTO;
import com.example.umc_10th.domain.mission.enums.MissionStatus;
import com.example.umc_10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc_10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MissionController {

    // 사용자 지역 기반 미션 조회
    @GetMapping("/v1/users/locations/missions")
    public ResponseEntity<ApiResponse<List<MissionResDTO.GetNearby>>> getUserMissions() {

        Long memberId = 1L;

        String userRegion = "서울특별시 강남구";

        List<MissionResDTO.GetNearby> missions = List.of(
                MissionResDTO.GetNearby.builder()
                        .storeName("치킨치킨")
                        .missionTitle("치킨 1마리 주문 미션")
                        .rewardPoint(500)
                        .build(),

                MissionResDTO.GetNearby.builder()
                        .storeName("버거버거")
                        .missionTitle("새우버거 구매 미션")
                        .rewardPoint(700)
                        .build()
        );

        return ResponseEntity.ok(
                ApiResponse.onSuccess(MissionSuccessCode.OK, missions)
        );
    }

    // 사용자 미션 목록 조회 (상태별)
    @GetMapping("/v1/users/missions")
    public ResponseEntity<ApiResponse<List<MissionResDTO.GetNearby>>> getUserMissions(
            @RequestParam MissionStatus status
    ) {

        Long memberId = 1L;

        // 상태별 분기
        List<MissionResDTO.GetNearby> missions;

        if (status == MissionStatus.IN_PROGRESS) {
            missions = List.of(
                    MissionResDTO.GetNearby.builder()
                            .storeName("마들렌 카페")
                            .missionTitle("마들렌 주문 미션")
                            .rewardPoint(500)
                            .build()
            );

        } else {
            missions = List.of(
                    MissionResDTO.GetNearby.builder()
                            .storeName("빙수다")
                            .missionTitle("팥빙수 구매 미션")
                            .rewardPoint(700)
                            .build()
            );
        }

        return ResponseEntity.ok(
                ApiResponse.onSuccess(MissionSuccessCode.OK, missions)
        );
    }

    // 미션 성공 누르기
    @PatchMapping("/{missionId}/complete")
    public ResponseEntity<ApiResponse<MissionResDTO.CompleteMission>> completeMission(
            @PathVariable Long missionId
    ) {

        Long memberId = 1L;

        MissionResDTO.CompleteMission response =
                MissionResDTO.CompleteMission.builder()
                        .missionId(missionId)
                        .status(MissionStatus.COMPLETED)
                        .build();

        return ResponseEntity.ok(
                ApiResponse.onSuccess(MissionSuccessCode.MISSION_COMPLETED, response)
        );
    }
}

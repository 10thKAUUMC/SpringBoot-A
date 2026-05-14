package com.example.umc_10th.domain.mission.controller;

import com.example.umc_10th.domain.mission.dto.MissionReqDTO;
import com.example.umc_10th.domain.mission.dto.MissionResDTO;
import com.example.umc_10th.domain.mission.enums.MissionStatus;
import com.example.umc_10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc_10th.domain.mission.service.MemberMissionService;
import com.example.umc_10th.domain.mission.service.MissionService;
import com.example.umc_10th.global.apiPayload.ApiResponse;
import com.example.umc_10th.global.apiPayload.code.BaseSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MissionController {

    private final MemberMissionService memberMissionService;
    private final MissionService missionService;

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

    // 미션 성공 누르기
    @PatchMapping("/v1/{missionId}/complete")
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

    // 사용자 미션 목록 조회 (상태별)
    @PostMapping("/v1/users/missions")
    public ResponseEntity<ApiResponse<List<MissionResDTO.GetUserMissions>>> getUserMissions(
            @RequestBody MissionReqDTO.GetMissions dto
    ) {
        List<MissionResDTO.GetUserMissions> missions =
                memberMissionService.getUserMissions(
                dto.memberId(),
                dto.status()
                );
        return ResponseEntity.ok(
                ApiResponse.onSuccess(MissionSuccessCode.OK, missions)
        );
    }

    // 가게 미션 생성
    @PostMapping("/v1/stores/{storeId}/missions")
    public ApiResponse<Void> createMission(
            @PathVariable Long storeId,
            @RequestBody @Valid MissionReqDTO.CreateMission dto
    ){
        BaseSuccessCode code = MissionSuccessCode.CREATED;
        return ApiResponse.onSuccess(code, missionService.createMission(storeId, dto));
    }

    // 가게 내 미션들 조회
    @GetMapping("/v1/stores/{storeId}/missions")
    public ApiResponse<MissionResDTO.Pagenation<MissionResDTO.GetStoreMissions>> getStoreMissions(
            @PathVariable Long storeId,
            @RequestParam Integer pageSize,
            @RequestParam String cursor,
            @RequestParam String query){
        BaseSuccessCode code = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(code, missionService.getStoreMissions(storeId, pageSize, cursor, query));
    }
}

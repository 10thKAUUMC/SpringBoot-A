package com.example.umc_10th.domain.mission.dto;

import com.example.umc_10th.domain.mission.enums.MissionStatus;
import lombok.Builder;

public class MissionResDTO {
    // 사용자 지역 기반 미션 조회
    @Builder
    public record GetNearby(
            String storeName,
            String missionTitle,
            Integer rewardPoint
    ) {}

    //미션 성공 누르기
    @Builder
    public record CompleteMission(
            Long missionId,
            MissionStatus status
    ) {}
}

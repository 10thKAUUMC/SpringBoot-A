package com.example.umc_10th.domain.mission.dto;

import com.example.umc_10th.domain.mission.enums.MissionStatus;
import lombok.Builder;

import java.time.LocalDateTime;

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

    // 사용자 미션 목록 조회 (상태별)
    @Builder
    public record GetUserMission(
            String storeName,
            String title,
            Integer point,
            MissionStatus status,
            LocalDateTime dueDate
    ) {}
}

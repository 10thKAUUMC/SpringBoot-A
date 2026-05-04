package com.example.umc10th.domain.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class MissionResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionViewDTO {
        Long missionId;
        Integer reward;     // reward after completing a mission
        LocalDate deadline; // Due date
        String missionSpec; // Mission description (예: 15,000원 이상 구매)
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionViewListDTO {
        List<MissionViewDTO> missionList; // list where it collects multiple store's info
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionCompleteResultDTO {
        Long missionId;
        LocalDateTime completedAt;
    }
}
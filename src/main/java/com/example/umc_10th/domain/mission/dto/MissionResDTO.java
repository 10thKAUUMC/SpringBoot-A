package com.example.umc_10th.domain.mission.dto;

import lombok.Builder;
import java.util.List;
import com.example.umc_10th.domain.mission.enums.MissionStatus;
public class MissionResDTO {

    @Builder
    public record MissionListDTO(
            List<MissionDTO> missions
    ) {}

    @Builder
    public record MissionDTO(
            Long missionId,
            Long storeId,
            String missionTitle,
            Integer missionPoint,
            String missionSpec,
            Integer deadline
    ) {}

    @Builder
    public record SuccessResultDTO(
            Long missionId,
            MissionStatus state
    ) {}
}

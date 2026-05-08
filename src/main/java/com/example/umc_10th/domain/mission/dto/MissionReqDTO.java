package com.example.umc_10th.domain.mission.dto;

import com.example.umc_10th.domain.mission.enums.MissionStatus;
import jakarta.validation.constraints.NotNull;

public class MissionReqDTO {

    public record SuccessDTO(
            MissionStatus state
    ) {}

    public record MyMissionDTO(
            @NotNull(message = "사용자 ID는 필수입니다.")
            Long memberId
    ) {}
}

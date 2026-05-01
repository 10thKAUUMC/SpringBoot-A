package com.example.umc_10th.domain.mission.dto;

import com.example.umc_10th.domain.mission.enums.MissionStatus;

public class MissionReqDTO {

    public record SuccessDTO(
            MissionStatus state
    ) {}
}

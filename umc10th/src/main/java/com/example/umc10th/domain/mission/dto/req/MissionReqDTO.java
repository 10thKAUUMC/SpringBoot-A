package com.example.umc10th.domain.mission.dto.req;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class MissionReqDTO {

    public record CreateMission(
            @NotNull
            LocalDate deadline,
            @NotNull
            @Min(0)
            Integer point,
            @NotBlank
            String conditional
    ) {
    }
}

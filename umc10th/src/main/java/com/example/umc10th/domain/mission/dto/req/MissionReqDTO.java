package com.example.umc10th.domain.mission.dto.req;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class MissionReqDTO {

    public record CreateMission(
            LocalDate deadline,
            Integer point,
            String conditional
    ) {
    }

    public record MissionListQuery(
            @JsonProperty("is_complete")
            boolean isComplete
    ) {
    }
}

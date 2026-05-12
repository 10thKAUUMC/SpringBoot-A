package com.example.umc10th.domain.mission.dto.req;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MissionReqDTO {

    public record MissionListQuery(
            @JsonProperty("is_complete")
            boolean isComplete
    ) {
    }
}

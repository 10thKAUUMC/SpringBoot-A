package com.example.umc10th.domain.mission.dto.req;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class MissionReqDTO {

    public record MissionListRequest(
            @JsonProperty("member_id")
            Long memberId,
            @JsonProperty("is_complete")
            Boolean isComplete,
            Integer page,
            Integer size
    ) {
        public boolean resolveIsComplete() {
            return Boolean.TRUE.equals(isComplete);
        }

        public int resolvePage() {
            return page == null || page < 1 ? 1 : page;
        }
    }

    public record CreateMission(
            LocalDate deadline,
            Integer point,
            String conditional
    ) {
    }
}

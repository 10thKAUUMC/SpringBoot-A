package com.example.umc10th.domain.home.dto.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;

public class HomeResDTO {

    @Builder
    public record MyMissions(
            String address,
            @JsonProperty("my_point")
            Integer myPoint,
            @JsonProperty("progress_mission")
            Integer progressMission,
            @JsonProperty("target_mission")
            Integer targetMission,
            @JsonProperty("progress_point")
            Integer progressPoint,
            List<MissionSummary> missions,
            @JsonProperty("next_cursor")
            String nextCursor,
            @JsonProperty("has_next")
            Boolean hasNext
    ) {
    }

    @Builder
    public record MissionSummary(
            @JsonProperty("store_id")
            Long storeId,
            @JsonProperty("store_name")
            String storeName,
            @JsonProperty("store_category")
            String storeCategory,
            @JsonProperty("d_day")
            Integer dDay,
            @JsonProperty("min_price")
            Integer minPrice,
            @JsonProperty("accumulate_point")
            Integer accumulatePoint
    ) {
    }
}

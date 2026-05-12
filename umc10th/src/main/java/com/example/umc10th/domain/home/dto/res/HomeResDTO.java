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
            int page,
            int size,
            @JsonProperty("total_elements")
            long totalElements,
            @JsonProperty("total_pages")
            int totalPages,
            @JsonProperty("has_next")
            boolean hasNext
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

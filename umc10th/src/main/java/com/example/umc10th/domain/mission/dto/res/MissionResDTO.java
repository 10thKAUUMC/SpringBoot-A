package com.example.umc10th.domain.mission.dto.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;

public class MissionResDTO {

    @Builder
    public record MissionList(
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
            @JsonProperty("min_price")
            Integer minPrice,
            @JsonProperty("point_percent")
            Integer pointPercent,
            @JsonProperty("is_complete")
            boolean isComplete
    ) {
    }

    @Builder
    public record MissionComplete(
            @JsonProperty("is_complete")
            boolean isComplete,
            String message
    ) {
    }
}

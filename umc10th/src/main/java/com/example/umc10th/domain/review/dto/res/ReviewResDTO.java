package com.example.umc10th.domain.review.dto.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;

public class ReviewResDTO {

    @Builder
    public record CreateReview(
            @JsonProperty("review_id")
            Long reviewId,
            String message
    ) {
    }

    @Builder
    public record MyReviewList(
            List<MyReviewItem> reviews,
            @JsonProperty("next_cursor")
            String nextCursor,
            @JsonProperty("has_next")
            boolean hasNext
    ) {
    }

    @Builder
    public record MyReviewItem(
            @JsonProperty("review_id")
            Long reviewId,
            @JsonProperty("star_point")
            Double starPoint,
            @JsonProperty("review_content")
            String reviewContent,
            @JsonProperty("store_id")
            Long storeId,
            @JsonProperty("store_name")
            String storeName
    ) {
    }
}

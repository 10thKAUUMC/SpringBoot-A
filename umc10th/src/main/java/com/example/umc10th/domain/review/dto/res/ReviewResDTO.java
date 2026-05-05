package com.example.umc10th.domain.review.dto.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

public class ReviewResDTO {

    @Builder
    public record CreateReview(
            @JsonProperty("review_id")
            Long reviewId,
            String message
    ) {
    }
}

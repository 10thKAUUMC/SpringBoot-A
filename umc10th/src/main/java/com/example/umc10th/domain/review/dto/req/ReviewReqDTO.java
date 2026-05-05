package com.example.umc10th.domain.review.dto.req;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReviewReqDTO {

    public record CreateReview(
            @JsonProperty("store_id")
            Long storeId,
            @JsonProperty("store_name")
            String storeName,
            @JsonProperty("star_point")
            Double starPoint,
            @JsonProperty("review_content")
            String reviewContent
    ) {
    }
}

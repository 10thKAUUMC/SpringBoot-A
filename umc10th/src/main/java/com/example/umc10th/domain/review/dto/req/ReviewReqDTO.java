package com.example.umc10th.domain.review.dto.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ReviewReqDTO {

    public record CreateReview(
            @JsonProperty("store_id")
            @NotNull
            Long storeId,
            @JsonProperty("store_name")
            String storeName,
            @JsonProperty("star_point")
            @NotNull
            @DecimalMin(value = "0.0", inclusive = true)
            @DecimalMax(value = "5.0", inclusive = true)
            Double starPoint,
            @JsonProperty("review_content")
            @NotBlank
            String reviewContent
    ) {
    }
}

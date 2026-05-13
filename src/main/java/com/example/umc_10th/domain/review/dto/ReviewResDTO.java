package com.example.umc_10th.domain.review.dto;

import lombok.Builder;

public class ReviewResDTO {

    @Builder
    public record CreateResultDTO(
            Long reviewId,
            Integer score,
            String body
    ) {}
}

package com.example.umc_10th.domain.review.dto;

import lombok.Builder;

public class ReviewResDTO {
    @Builder
    public record Create(
            Long reviewId,
            Long storeId,
            Integer star,
            String content
    ) {}
}

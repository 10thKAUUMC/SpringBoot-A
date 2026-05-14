package com.example.umc_10th.domain.review.dto;

import lombok.Builder;

import java.time.LocalDateTime;

public class ReviewResDTO {
    @Builder
    public record Create(
            Long reviewId,
            LocalDateTime createdAt
    ) {}
}

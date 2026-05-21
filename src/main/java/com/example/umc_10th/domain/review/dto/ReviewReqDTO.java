package com.example.umc_10th.domain.review.dto;

import jakarta.validation.constraints.NotNull;

public class ReviewReqDTO {

    public record Create(
            Long userId,
            Long storeId,
            Float star,
            String content
    ) {}

    public  record GetMyReviews(
            @NotNull(message = "userid는 필수입니다.")
            Long userId,
            @NotNull(message = "storeId는 필수입니다.")
            Long storeId
    ){}
}

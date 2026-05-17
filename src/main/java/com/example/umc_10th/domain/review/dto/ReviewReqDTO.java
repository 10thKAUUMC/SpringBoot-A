package com.example.umc_10th.domain.review.dto;

public class ReviewReqDTO {

    public record Create(
            Long userId,
            Long storeId,
            Float star,
            String content
    ) {}

    public  record GetMyReviews(
            Long userId,
            Long storeId
    ){}
}

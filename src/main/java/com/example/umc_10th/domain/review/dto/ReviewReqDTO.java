package com.example.umc_10th.domain.review.dto;

public class ReviewReqDTO {

    public record CreateDTO(
            Integer score,
            String body,
            Long storeId,
            String userId
    ) {}
}

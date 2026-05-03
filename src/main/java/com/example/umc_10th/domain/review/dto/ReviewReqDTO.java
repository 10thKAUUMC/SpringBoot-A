package com.example.umc_10th.domain.review.dto;

public class ReviewReqDTO {

    public record Create(
            Integer star,
            String content
    ) {}
}

package com.example.umc_10th.domain.review.dto;

import lombok.Builder;

import java.util.List;

public class ReviewResDTO {

    @Builder
    public record CreateResultDTO(
            Long reviewId,
            Integer score,
            String body
    ) {}

    // 내가 작성한 리뷰 목록 응답 DTO
    @Builder
    public record MyReviewListDTO(
            List<MyReviewDTO> reviews,
            Long nextCursorId,
            Integer nextCursorScore,
            Boolean hasNext
    ) {}

    // 리뷰 개별 응답 DTO
    @Builder
    public record MyReviewDTO(
            Long reviewId,
            Long storeId,
            String storeName,
            Integer score,
            String body
    ) {}
}

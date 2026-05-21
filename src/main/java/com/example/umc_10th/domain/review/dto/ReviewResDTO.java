package com.example.umc_10th.domain.review.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDTO {
    @Builder
    public record Create(
            Long reviewId,
            LocalDateTime createdAt
    ) {}

    //
    @Builder
    public record GetMyReviews(
            Long reviewId,
            String content,
            Float star,
            String memberNickname,
            String storeName
    ){}

    // 페이지네이션 틀
    @Builder
    public record Pagenation<T>(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ){}
}

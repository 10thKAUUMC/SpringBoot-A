package com.example.umc_10th.domain.review.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ReviewReqDTO {

    public record CreateDTO(
            @NotNull(message = "별점은 필수입니다.")
            @Min(value = 1, message = "별점은 최소 1점입니다.")
            @Max(value = 5, message = "별점은 최대 5점입니다.")
            Integer score,

            @NotBlank(message = "리뷰 내용은 필수입니다.")
            String body,

            @NotNull(message = "가게 ID는 필수입니다.")
            Long storeId,

            @NotNull(message = "사용자 ID는 필수입니다.")
            Long userId
    ) {}

    // 내가 작성한 리뷰 ID 순 조회 요청 DTO
    public record MyReviewByIdDTO(
            @NotNull(message = "사용자 ID는 필수입니다.")
            Long memberId,
            Long cursorId
    ) {}

    // 내가 작성한 리뷰 별점 순 조회 요청 DTO
    public record MyReviewByScoreDTO(
            @NotNull(message = "사용자 ID는 필수입니다.")
            Long memberId,
            Long cursorId,
            Integer cursorScore
    ) {}
}

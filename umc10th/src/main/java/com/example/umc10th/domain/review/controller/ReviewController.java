package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.req.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.res.ReviewResDTO;
import com.example.umc10th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "가게 리뷰", description = "가게에 대한 리뷰 작성")
@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class ReviewController {

    private final ReviewService reviewService;

    @Operation(
            summary = "가게 리뷰 작성",
            description = """회원이 특정 가게에 리뷰(별점·내용)를 등록합니다."""
    )
    @PostMapping("/{storeId}/reviews")
    public ApiResponse<ReviewResDTO.CreateReview> createReview(
            @Parameter(description = "가게 PK(본문의 store_id와 일치)", required = true, example = "1")
            @PathVariable Long storeId,
            @Parameter(description = "작성자 회원 PK", example = "1")
            @RequestParam(name = "member_id", required = false, defaultValue = "1") Long memberId,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "리뷰 내용·별점 등")
            @Valid @RequestBody ReviewReqDTO.CreateReview request
    ) {
        ReviewResDTO.CreateReview result = reviewService.createReview(storeId, memberId, request);
        BaseSuccessCode code = ReviewSuccessCode.REVIEW_CREATED;
        return ApiResponse.onSuccess(code, result);
    }
}

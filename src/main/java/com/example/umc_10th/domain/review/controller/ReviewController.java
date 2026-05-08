package com.example.umc_10th.domain.review.controller;

import com.example.umc_10th.domain.review.dto.ReviewReqDTO;
import com.example.umc_10th.domain.review.dto.ReviewResDTO;
import com.example.umc_10th.domain.review.service.ReviewService;
import com.example.umc_10th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

// 마이페이지 리뷰 작성 전용 Controller 입니다 !
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/reviews")
    public ApiResponse<ReviewResDTO.CreateResultDTO> createReview(
            @RequestBody @Valid ReviewReqDTO.CreateDTO request
    ) {
        return ApiResponse.onSuccess(
                reviewService.createReview(request)
        );
    }

    // 7주차 미션: 내가 작성한 리뷰 ID 순 조회
    @PostMapping("/reviews/my/id")
    public ApiResponse<ReviewResDTO.MyReviewListDTO> getMyReviewsOrderById(
            @RequestBody @Valid ReviewReqDTO.MyReviewByIdDTO request,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        return ApiResponse.onSuccess(
                reviewService.getMyReviewsOrderById(
                        request.memberId(),
                        request.cursorId(),
                        size
                )
        );
    }

    // 7주차 미션: 내가 작성한 리뷰 별점 순 조회
    @PostMapping("/reviews/my/score")
    public ApiResponse<ReviewResDTO.MyReviewListDTO> getMyReviewsOrderByScore(
            @RequestBody @Valid ReviewReqDTO.MyReviewByScoreDTO request,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        return ApiResponse.onSuccess(
                reviewService.getMyReviewsOrderByScore(
                        request.memberId(),
                        request.cursorId(),
                        request.cursorScore(),
                        size
                )
        );
    }
}

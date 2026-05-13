package com.example.umc_10th.domain.review.controller;

import com.example.umc_10th.domain.review.dto.ReviewReqDTO;
import com.example.umc_10th.domain.review.dto.ReviewResDTO;
import com.example.umc_10th.domain.review.service.ReviewService;
import com.example.umc_10th.global.apiPayload.ApiResponse;
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
            @RequestBody ReviewReqDTO.CreateDTO request
    ) {
        return ApiResponse.onSuccess(
                reviewService.createReview(request)
        );
    }
}

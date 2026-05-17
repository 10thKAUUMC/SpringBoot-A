package com.example.umc_10th.domain.review.controller;

import com.example.umc_10th.domain.mission.dto.MissionResDTO;
import com.example.umc_10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc_10th.domain.review.dto.ReviewReqDTO;
import com.example.umc_10th.domain.review.dto.ReviewResDTO;
import com.example.umc_10th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc_10th.domain.review.service.ReviewService;
import com.example.umc_10th.global.apiPayload.ApiResponse;
import com.example.umc_10th.global.apiPayload.code.BaseSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/v1/users/stores/{storeId}/reviews")
    public ResponseEntity<ApiResponse<ReviewResDTO.Create>> createReview(
            @RequestBody ReviewReqDTO.Create request
    ) {
        Long memberId = 1L;

        // ReviewService 호출
        ReviewResDTO.Create response = reviewService.createReview(memberId, request);

        return ResponseEntity.ok(
                ApiResponse.onSuccess(ReviewSuccessCode.CREATED, response)
        );
    }

    // 내가 작성한 리뷰 조회
    @PostMapping("/v1/users/reviews")
    public ApiResponse<ReviewResDTO.Pagenation<ReviewResDTO.GetMyReviews>> getStoreMissions(
            @RequestBody @Valid ReviewReqDTO.GetMyReviews request,
            @RequestParam Integer pageSize,
            @RequestParam String cursor,
            @RequestParam String query){
        BaseSuccessCode code = ReviewSuccessCode.OK;
        return ApiResponse.onSuccess(code, reviewService.getMyReviews(request.userId(), request.userId(), pageSize, cursor, query));
    }}

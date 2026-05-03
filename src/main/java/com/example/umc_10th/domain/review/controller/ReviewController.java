package com.example.umc_10th.domain.review.controller;

import com.example.umc_10th.domain.review.dto.ReviewReqDTO;
import com.example.umc_10th.domain.review.dto.ReviewResDTO;
import com.example.umc_10th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc_10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users/stores")
public class ReviewController {

    @PostMapping("/{storeId}/reviews")
    public ResponseEntity<ApiResponse<ReviewResDTO.Create>> createReview(
            @PathVariable Long storeId,
            @RequestBody ReviewReqDTO.Create request
    ) {

        Long memberId = 1L;

        ReviewResDTO.Create response =
                ReviewResDTO.Create.builder()
                        .reviewId(1L) // 임시 (나중에 DB 생성값)
                        .storeId(storeId)
                        .star(request.star())
                        .content(request.content())
                        .build();

        return ResponseEntity.ok(
                ApiResponse.onSuccess(ReviewSuccessCode.CREATED, response)
        );
    }
}

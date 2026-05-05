package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.req.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.res.ReviewResDTO;
import com.example.umc10th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stores")
public class ReviewController {

    @PostMapping("/{storeId}/reviews")
    public ApiResponse<ReviewResDTO.CreateReview> createReview(
            @PathVariable Long storeId,
            @RequestBody ReviewReqDTO.CreateReview request
    ) {
        ReviewResDTO.CreateReview result = ReviewResDTO.CreateReview.builder()
                .reviewId(1L)
                .message("성공적으로 리뷰를 작성하였습니다.")
                .build();

        BaseSuccessCode code = ReviewSuccessCode.REVIEW_CREATED;
        return ApiResponse.onSuccess(code, result);
    }
}

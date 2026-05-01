package com.example.umc_10th.domain.review.service;

import com.example.umc_10th.domain.review.dto.ReviewReqDTO;
import com.example.umc_10th.domain.review.dto.ReviewResDTO;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    public ReviewResDTO.CreateResultDTO createReview(ReviewReqDTO.CreateDTO request) {
        return ReviewResDTO.CreateResultDTO.builder()
                .reviewId(1L)
                .score(request.score())
                .body(request.body())
                .build();
    }
}

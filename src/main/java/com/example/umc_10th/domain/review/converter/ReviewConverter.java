package com.example.umc_10th.domain.review.converter;

import com.example.umc_10th.domain.member.entity.Member;
import com.example.umc_10th.domain.review.dto.ReviewReqDTO;
import com.example.umc_10th.domain.review.dto.ReviewResDTO;
import com.example.umc_10th.domain.review.entity.Review;
import com.example.umc_10th.domain.store.entity.Store;

public class ReviewConverter {

    // DTO -> 엔티티 변환 (리뷰 생성)
    public static Review toEntity(ReviewReqDTO.Create request, Member member, Store store) {
        return Review.builder()
                .member(member)
                .store(store)
                .star(request.star())
                .content(request.content())
                .build();
    }

    // 엔티티 -> DTO 변환 (응답 생성)
    public static ReviewResDTO.Create toCreateResponse(Review review) {
        return ReviewResDTO.Create.builder()
                .reviewId(review.getId())
                .createdAt(review.getCreatedAt())
                .build();
    }
}

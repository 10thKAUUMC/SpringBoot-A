package com.example.umc_10th.domain.review.converter;

import com.example.umc_10th.domain.member.entity.Member;
import com.example.umc_10th.domain.mission.dto.MissionResDTO;
import com.example.umc_10th.domain.mission.entity.Mission;
import com.example.umc_10th.domain.review.dto.ReviewReqDTO;
import com.example.umc_10th.domain.review.dto.ReviewResDTO;
import com.example.umc_10th.domain.review.entity.Review;
import com.example.umc_10th.domain.store.entity.Store;

import java.util.List;

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

    // 내가 작성한 리뷰 조회
    public static ReviewResDTO.GetMyReviews toGetMyReviews(
            Review review
    ){
        return ReviewResDTO.GetMyReviews.builder()
                .reviewId(review.getId())
                .content(review.getContent())
                .star(review.getStar())
                .memberNickname(review.getMember().getNickname())
                .storeName(review.getStore().getName())
                .build();
    }

    // 페이지네이션 틀 생성
    public static <T> ReviewResDTO.Pagenation<T> toPagination(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ){
        return ReviewResDTO.Pagenation.<T>builder()
                .data(data)
                .hasNext(hasNext)
                .nextCursor(nextCursor)
                .pageSize(pageSize)
                .build();
    }
}

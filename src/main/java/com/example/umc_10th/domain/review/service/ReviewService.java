package com.example.umc_10th.domain.review.service;

import com.example.umc_10th.domain.member.entity.Member;
import com.example.umc_10th.domain.member.exception.MemberException;
import com.example.umc_10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc_10th.domain.review.converter.ReviewConverter;
import com.example.umc_10th.domain.review.dto.ReviewReqDTO;
import com.example.umc_10th.domain.review.dto.ReviewResDTO;
import com.example.umc_10th.domain.review.entity.Review;
import com.example.umc_10th.domain.review.repository.ReviewRepository;
import com.example.umc_10th.domain.store.entity.Store;
import com.example.umc_10th.domain.store.exception.StoreException;
import com.example.umc_10th.domain.store.exception.code.StoreErrorCode;
import com.example.umc_10th.domain.member.repository.MemberRepository;
import com.example.umc_10th.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    @Transactional
    public ReviewResDTO.Create createReview(Long memberId, ReviewReqDTO.Create request) {
        // 사용자와 가게가 유효한지 확인
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
        Store store = storeRepository.findById(request.storeId())
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        // Review 엔티티 생성
        Review review = ReviewConverter.toEntity(request, member, store);

        // 저장 후 응답 DTO 반환
        return ReviewConverter.toCreateResponse(reviewRepository.save(review));
    }
}

package com.example.umc_10th.domain.review.service;

import com.example.umc_10th.domain.member.entity.Member;
import com.example.umc_10th.domain.member.exception.MemberException;
import com.example.umc_10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc_10th.domain.review.converter.ReviewConverter;
import com.example.umc_10th.domain.review.dto.ReviewReqDTO;
import com.example.umc_10th.domain.review.dto.ReviewResDTO;
import com.example.umc_10th.domain.review.entity.Review;
import com.example.umc_10th.domain.review.exception.ReviewException;
import com.example.umc_10th.domain.review.exception.code.ReviewErrorCode;
import com.example.umc_10th.domain.review.repository.ReviewRepository;
import com.example.umc_10th.domain.store.entity.Store;
import com.example.umc_10th.domain.store.exception.StoreException;
import com.example.umc_10th.domain.store.exception.code.StoreErrorCode;
import com.example.umc_10th.domain.member.repository.MemberRepository;
import com.example.umc_10th.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
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

    //내가 작성한 리뷰 조회
    public ReviewResDTO.Pagenation<ReviewResDTO.GetMyReviews> getMyReviews(
            Long storeId,
            Long memberId,
            Integer pageSize,
            String cursor,
            String sort
    ) {
        // 기본 정렬: id
        String sortOption = (sort == null || sort.isBlank()) ? "id" : sort.toLowerCase();
        PageRequest pageRequest = PageRequest.of(0, pageSize);

        Slice<Review> reviewsList;
        String nextCursor = null;
        boolean isFirstRequest =
                cursor == null || cursor.equals("-1");

        // 커서가 있을 때: 커서를 분리해서 페이징 처리
        if (!isFirstRequest) {
            String[] cursorParts = cursor.split(":");
            switch (sortOption) {
                case "id": {
                    // id 기반 페이징 처리
                    Long idCursor = Long.parseLong(cursorParts[1]);
                    reviewsList = reviewRepository.findReviewByStore_IdAndMember_IdAndIdLessThanOrderByIdDesc(
                            storeId, memberId, idCursor, pageRequest
                    );
                    break;
                }
                case "rating": {
                    // rating 기반 페이징 처리
                    Float ratingCursor = Float.parseFloat(cursorParts[0]);
                    Long idCursor = Long.parseLong(cursorParts[1]);
                    reviewsList = reviewRepository.findReviewsByRatingWithCursor(
                            storeId, memberId, ratingCursor, idCursor, pageRequest
                    );
                    break;
                }
                default:
                    throw new ReviewException(ReviewErrorCode.QUERY_NOT_VALID); // 잘못된 정렬 방식 처리
            }
        } else {
            switch (sortOption) {
                case "id": {
                    // 처음 요청(id 정렬)
                    reviewsList = reviewRepository.findReviewByStore_IdAndMember_IdOrderByIdDesc(
                            storeId, memberId, pageRequest
                    );
                    break;
                }
                case "rating": {
                    // 처음 요청(rating 정렬)
                    reviewsList = reviewRepository.findReviewsByRatingWithoutCursor(
                            storeId, memberId, pageRequest
                    );
                    break;
                }
                default:
                    throw new ReviewException(ReviewErrorCode.QUERY_NOT_VALID);
            }
        }

        // 다음 커서 계산
        if (!reviewsList.isEmpty()) {
            Review lastReview = reviewsList.getContent().getLast();
            switch (sortOption) {
                case "id": {
                    nextCursor = lastReview.getId() + ":" + lastReview.getId();
                    break;
                }
                case "rating": {
                    nextCursor = lastReview.getStar() + ":" + lastReview.getId();
                    break;
                }
            }
        }

        // 결과를 DTO로 변환
        return ReviewConverter.toPagination(
                reviewsList.map(ReviewConverter::toGetMyReviews).toList(),
                reviewsList.hasNext(),
                nextCursor,
                reviewsList.getSize()
        );
    }
}

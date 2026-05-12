package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exeption.MemberException;
import com.example.umc10th.domain.member.exeption.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.review.dto.req.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.res.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import com.example.umc10th.domain.review.repository.projection.MyReviewCursorRow;
import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.domain.store.repository.StoreRepository;
import com.example.umc10th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc10th.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private static final int DEFAULT_PAGE_SIZE = 10;

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public ReviewResDTO.CreateReview createReview(
            Long storeId,
            Long memberId,
            ReviewReqDTO.CreateReview request
    ) {
        if (!storeId.equals(request.storeId())) {
            throw new GeneralException(GeneralErrorCode.BAD_REQUEST);
        }
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND));
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        Review review = Review.builder()
                .starPoint(request.starPoint())
                .reviewContent(request.reviewContent())
                .member(member)
                .store(store)
                .build();

        Review saved = reviewRepository.save(review);

        return ReviewResDTO.CreateReview.builder()
                .reviewId(saved.getId())
                .message("성공적으로 리뷰를 작성하였습니다.")
                .build();
    }

    @Transactional(readOnly = true)
    public ReviewResDTO.MyReviewList getMyReviewList(
            Long memberId,
            String sort,
            String cursor,
            Integer size
    ) {
        memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        int pageSize = size == null || size < 1 ? DEFAULT_PAGE_SIZE : size;
        String sortKey = sort == null ? "id" : sort.trim().toLowerCase();
        String cursorParam = emptyToNull(cursor);

        List<MyReviewCursorRow> raw = switch (sortKey) {
            case "id" -> reviewRepository.findMyReviewsWithCursorOrderById(
                    memberId, cursorParam, pageSize + 1);
            case "star" -> reviewRepository.findMyReviewsWithCursorOrderByStar(
                    memberId, cursorParam, pageSize + 1);
            default -> throw new GeneralException(GeneralErrorCode.BAD_REQUEST);
        };

        boolean hasNext = raw.size() > pageSize;
        List<MyReviewCursorRow> slice = hasNext ? raw.subList(0, pageSize) : raw;
        String nextCursor = null;
        if (hasNext && !slice.isEmpty()) {
            nextCursor = slice.get(slice.size() - 1).getCursorValue();
        }

        List<ReviewResDTO.MyReviewItem> reviews = new ArrayList<>();
        for (MyReviewCursorRow row : slice) {
            reviews.add(ReviewResDTO.MyReviewItem.builder()
                    .reviewId(row.getReviewId())
                    .starPoint(row.getStarPoint())
                    .reviewContent(row.getReviewContent())
                    .storeId(row.getStoreId())
                    .storeName(row.getStoreName())
                    .build());
        }

        return ReviewResDTO.MyReviewList.builder()
                .reviews(reviews)
                .nextCursor(nextCursor)
                .hasNext(hasNext)
                .build();
    }

    private static String emptyToNull(String cursor) {
        return (cursor == null || cursor.isBlank()) ? null : cursor;
    }
}

package com.example.umc_10th.domain.review.service;

import com.example.umc_10th.domain.review.dto.ReviewReqDTO;
import com.example.umc_10th.domain.review.dto.ReviewResDTO;
import com.example.umc_10th.domain.review.entity.Review;
import com.example.umc_10th.domain.review.repository.ReviewRepository;
import com.example.umc_10th.domain.store.entity.Store;
import com.example.umc_10th.domain.store.repository.StoreRepository;
import com.example.umc_10th.domain.users.entity.User;
import com.example.umc_10th.domain.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;

    public ReviewResDTO.CreateResultDTO createReview(ReviewReqDTO.CreateDTO request) {

        Store store = storeRepository.findById(request.storeId())
                .orElseThrow(() -> new RuntimeException("가게를 찾을 수 없습니다."));

        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new RuntimeException("회원을 찾을 수 없습니다."));

        Review review = Review.builder()
                .score(request.score())
                .body(request.body())
                .store(store)
                .user(user)
                .build();

        Review savedReview = reviewRepository.save(review);

        return ReviewResDTO.CreateResultDTO.builder()
                .reviewId(savedReview.getId())
                .score(savedReview.getScore())
                .body(savedReview.getBody())
                .build();
    }

    // 7주차 미션: 내가 작성한 리뷰 ID 순 조회
    @Transactional(readOnly = true)
    public ReviewResDTO.MyReviewListDTO getMyReviewsOrderById(
            Long memberId,
            Long cursorId,
            Integer size
    ) {
        User user = userRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("회원을 찾을 수 없습니다."));

        List<Review> reviews = reviewRepository.findMyReviewsOrderById(
                user,
                cursorId,
                PageRequest.of(0, size + 1)
        );

        boolean hasNext = reviews.size() > size;

        if (hasNext) {
            reviews = reviews.subList(0, size);
        }

        Long nextCursorId = reviews.isEmpty()
                ? null
                : reviews.get(reviews.size() - 1).getId();

        return ReviewResDTO.MyReviewListDTO.builder()
                .reviews(
                        reviews.stream()
                                .map(this::toMyReviewDTO)
                                .toList()
                )
                .nextCursorId(nextCursorId)
                .nextCursorScore(null)
                .hasNext(hasNext)
                .build();
    }

    // 7주차 미션: 내가 작성한 리뷰 별점 순 조회
    @Transactional(readOnly = true)
    public ReviewResDTO.MyReviewListDTO getMyReviewsOrderByScore(
            Long memberId,
            Long cursorId,
            Integer cursorScore,
            Integer size
    ) {
        User user = userRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("회원을 찾을 수 없습니다."));

        List<Review> reviews = reviewRepository.findMyReviewsOrderByScore(
                user,
                cursorScore,
                cursorId,
                PageRequest.of(0, size + 1)
        );

        boolean hasNext = reviews.size() > size;

        if (hasNext) {
            reviews = reviews.subList(0, size);
        }

        Long nextCursorId = reviews.isEmpty()
                ? null
                : reviews.get(reviews.size() - 1).getId();

        Integer nextCursorScore = reviews.isEmpty()
                ? null
                : reviews.get(reviews.size() - 1).getScore();

        return ReviewResDTO.MyReviewListDTO.builder()
                .reviews(
                        reviews.stream()
                                .map(this::toMyReviewDTO)
                                .toList()
                )
                .nextCursorId(nextCursorId)
                .nextCursorScore(nextCursorScore)
                .hasNext(hasNext)
                .build();
    }

    private ReviewResDTO.MyReviewDTO toMyReviewDTO(Review review) {
        return ReviewResDTO.MyReviewDTO.builder()
                .reviewId(review.getId())
                .storeId(review.getStore().getId())
                .storeName(review.getStore().getStoreName())
                .score(review.getScore())
                .body(review.getBody())
                .build();
    }
}

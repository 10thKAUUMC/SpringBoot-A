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
import org.springframework.stereotype.Service;

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
}

package com.example.umc10th.domain.user.application;

import com.example.umc10th.domain.review.dao.ReviewRepository;
import com.example.umc10th.domain.review.domain.Review;
import com.example.umc10th.domain.user.dao.UserRepository;
import com.example.umc10th.domain.user.domain.User;
import com.example.umc10th.domain.user.dto.UserRequestDTO;
import com.example.umc10th.domain.user.dto.UserResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

@Service //Take charge of business logic
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;

    @Transactional
    public UserResponseDTO.JoinResultDTO join(UserRequestDTO.JoinDTO request) {
        //Come up with a logic where it checks for email that is assigned

        //Converts DTO data into Entity
        User newUser = User.builder()
                .nickname(request.nickname())
                .email(request.email())
                .build();

        userRepository.save(newUser);

        return UserResponseDTO.JoinResultDTO.builder()
                .memberId(newUser.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    @Transactional
    public UserResponseDTO.PostReviewResultDTO postReview(Long userId, UserRequestDTO.PostReviewDTO request) {
        // 1. Logic to verify if the user exists by userID (to be added later)
        // 2. Logic to verify if the store exists using request.getStoreId() (to be added later)

        // 3. Review entity creation & persistence (returns mock data for now)
        return UserResponseDTO.PostReviewResultDTO.builder()
                .reviewId(100L) // Temporary ID
                .createdAt(LocalDateTime.now())
                .build();
    }

    public Slice<Review> getMyReviewList(Long userId, Long lastId, Float score, String sort, Pageable pageable) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("해당 유저를 찾을 수 없습니다."));

        if("score".equals(sort)) {
            return reviewRepository.findByUserAndScoreCursor(user, score, lastId, pageable);
        }

        return reviewRepository.findALLByUserAndIdLessThanOrderByIdDesc(user, lastId, pageable);
    }
}

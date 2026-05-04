package com.example.umc10th.domain.user.application;

import com.example.umc10th.domain.user.dao.UserRepository;
import com.example.umc10th.domain.user.domain.User;
import com.example.umc10th.domain.user.dto.UserRequestDTO;
import com.example.umc10th.domain.user.dto.UserResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service //Take charge of business logic
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private  final UserRepository userRepository;

    @Transactional
    public UserResponseDTO.JoinResultDTO join(UserRequestDTO.JoinDTO request) {
        //Come up with a logic where it checks for email that is assigned

        //Converts DTO data into Entity
        User newUser = User.builder()
                .nickname(request.getNickname())
                .email(request.getEmail())
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
                .reviewId(100L) // 임시 ID
                .createdAt(LocalDateTime.now())
                .build();
    }
}

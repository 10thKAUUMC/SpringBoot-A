package com.example.umc10th.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;


public class UserResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JoinResultDTO {
        Long memberId;
        LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PostReviewResultDTO {
        Long reviewId;
        LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserMissionPreViewListDTO {
        List<UserMissionPreViewDTO> missionList;
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Boolean isFirst;
        Boolean isLast;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserMissionPreViewDTO {
        Long missionId;
        String storeName;
        Integer reward;
        String missionSpec;
        String status;
        LocalDateTime createdAt;
    }

    public static class MyReviewPreViewListDTO {
        List<MyReviewPreViewDTO> reviewList;
        Integer listSize;
        Long lastId;      // For next return cursor value (ID)
        Float lastScore;  // For next return cursor value (Order by rating descending order)
        Boolean hasNext;  // Checking the Next Page exists
    }

    public static class MyReviewPreViewDTO {
        Long reviewId;
        String storeName;
        Float score;
        String body;
        LocalDateTime createdAt;
    }

}

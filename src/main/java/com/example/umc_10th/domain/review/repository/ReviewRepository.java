package com.example.umc_10th.domain.review.repository;

import com.example.umc_10th.domain.review.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // id로 정렬: 커서 없이 조회
    Slice<Review> findReviewByStore_IdAndMember_IdOrderByIdDesc(Long storeId, Long memberId, Pageable pageable);

    // id로 정렬: 커서가 있는 경우
    Slice<Review> findReviewByStore_IdAndMember_IdAndIdLessThanOrderByIdDesc(Long storeId, Long memberId, Long idCursor, Pageable pageable);

    // rating 기준: 커서 없이 조회
    @Query("SELECT r FROM Review r " +
           "WHERE r.store.id = :storeId AND r.member.id = :memberId " +
           "ORDER BY r.star DESC, r.id ASC")
    Slice<Review> findReviewsByRatingWithoutCursor(
            @Param("storeId") Long storeId,
            @Param("memberId") Long memberId,
            Pageable pageable
    );

    // rating 기준: 커서가 있는 경우
    @Query("SELECT r FROM Review r " +
           "WHERE r.store.id = :storeId AND r.member.id = :memberId " +
           "AND (r.star < :ratingCursor OR (r.star = :ratingCursor AND r.id < :idCursor)) " +
           "ORDER BY r.star DESC, r.id ASC")
    Slice<Review> findReviewsByRatingWithCursor(
            @Param("storeId") Long storeId,
            @Param("memberId") Long memberId,
            @Param("ratingCursor") Float ratingCursor,
            @Param("idCursor") Long idCursor,
            Pageable pageable
    );
}

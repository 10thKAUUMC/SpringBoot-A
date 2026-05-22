package com.example.umc_10th.domain.review.repository;

import com.example.umc_10th.domain.review.entity.Review;
import com.example.umc_10th.domain.users.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // 내가 작성한 리뷰 ID 순 조회
    @Query("""
            select r
            from Review r
            join fetch r.store s
            where r.user = :user
              and (:cursorId is null or r.id < :cursorId)
            order by r.id desc
            """)
    List<Review> findMyReviewsOrderById(
            @Param("user") User user,
            @Param("cursorId") Long cursorId,
            Pageable pageable
    );

    // 내가 작성한 리뷰 별점 순 조회
    @Query("""
            select r
            from Review r
            join fetch r.store s
            where r.user = :user
              and (
                    :cursorScore is null
                    or r.score < :cursorScore
                    or (r.score = :cursorScore and r.id < :cursorId)
                  )
            order by r.score desc, r.id desc
            """)
    List<Review> findMyReviewsOrderByScore(
            @Param("user") User user,
            @Param("cursorScore") Integer cursorScore,
            @Param("cursorId") Long cursorId,
            Pageable pageable
    );
}

package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.repository.projection.MyReviewCursorRow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query(value = """
            SELECT
                r.review_id AS reviewId,
                r.star_point AS starPoint,
                r.review_content AS reviewContent,
                r.store_id AS storeId,
                s.store_name AS storeName,
                LPAD(r.review_id, 10, '0') AS cursorValue
            FROM review r
            JOIN store s ON r.store_id = s.store_id
            WHERE r.member_id = :memberId
              AND (
                  :cursor IS NULL
                  OR :cursor = ''
                  OR r.review_id < CAST(:cursor AS UNSIGNED)
              )
            ORDER BY r.review_id DESC
            LIMIT :limit
            """, nativeQuery = true)
    List<MyReviewCursorRow> findMyReviewsWithCursorOrderById(
            @Param("memberId") Long memberId,
            @Param("cursor") String cursor,
            @Param("limit") int limit
    );

    @Query(value = """
            SELECT
                r.review_id AS reviewId,
                r.star_point AS starPoint,
                r.review_content AS reviewContent,
                r.store_id AS storeId,
                s.store_name AS storeName,
                CONCAT(
                    LPAD(FLOOR(r.star_point * 100), 5, '0'),
                    LPAD(r.review_id, 10, '0')
                ) AS cursorValue
            FROM review r
            JOIN store s ON r.store_id = s.store_id
            WHERE r.member_id = :memberId
              AND (
                  :cursor IS NULL
                  OR :cursor = ''
                  OR CONCAT(
                      LPAD(FLOOR(r.star_point * 100), 5, '0'),
                      LPAD(r.review_id, 10, '0')
                  ) < :cursor
              )
            ORDER BY r.star_point DESC, r.review_id DESC
            LIMIT :limit
            """, nativeQuery = true)
    List<MyReviewCursorRow> findMyReviewsWithCursorOrderByStar(
            @Param("memberId") Long memberId,
            @Param("cursor") String cursor,
            @Param("limit") int limit
    );
}

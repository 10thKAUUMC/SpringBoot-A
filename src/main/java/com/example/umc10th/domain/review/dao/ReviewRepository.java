package com.example.umc10th.domain.review.dao;

import com.example.umc10th.domain.review.domain.Review;
import com.example.umc10th.domain.user.domain.User;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.umc10th.domain.review.domain.Review;
import org.springframework.data.repository.query.Param;
import com.example.umc10th.domain.store.domain.Store;
import org.springframework.data.domain.Pageable;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT r FROM Review r Join FETCH r.user Where r.store = :store")
    Page<Review> findAllByStore(@Param("store") Store store, Pageable pageable);

    Slice<Review> findALLByUserAndIdLessThanOrderByIdDesc(User user, Long lastId, Pageable pageable);

    @Query("SELECT r FROM Review r WHERE r.user = :user AND (r.rating < :rating OR (r.rating = :rating AND r.id < :lastId)) ORDER BY r.rating DESC, r.id DESC")
    Slice<Review> findByUserAndScoreCursor(@Param("user") User user, @Param("score") Float score, @Param("lastId") Long lastId, Pageable pageable);

}

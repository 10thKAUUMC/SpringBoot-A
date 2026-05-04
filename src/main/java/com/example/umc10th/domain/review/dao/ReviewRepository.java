package com.example.umc10th.domain.review.dao;

import com.example.umc10th.domain.review.domain.Review;
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


}

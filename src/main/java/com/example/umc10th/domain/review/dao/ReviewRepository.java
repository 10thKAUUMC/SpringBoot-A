package com.example.umc10th.domain.review.dao;

import com.example.umc10th.domain.review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}

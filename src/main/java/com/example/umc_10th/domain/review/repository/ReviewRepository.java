package com.example.umc_10th.domain.review.repository;

import com.example.umc_10th.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}

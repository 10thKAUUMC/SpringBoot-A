package com.example.umc10th.domain.review.repository.projection;

public interface MyReviewCursorRow {

    Long getReviewId();

    Double getStarPoint();

    String getReviewContent();

    Long getStoreId();

    String getStoreName();

    String getCursorValue();
}

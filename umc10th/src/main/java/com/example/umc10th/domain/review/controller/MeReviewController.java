package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.res.ReviewResDTO;
import com.example.umc10th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/me")
public class MeReviewController {

    private final ReviewService reviewService;

    // 내가 작성한 리뷰 목록 (커서 페이징, 사진 제외).

    // @param memberId 회원 PK (가게 리뷰 작성 API와 동일하게 쿼리 파라미터)
    // @param sort     {@code id} — 리뷰 ID 내림차순, {@code star} — 별점 내림차순(동일 별점은 ID 내림차순)

    @GetMapping("/review-list")
    public ApiResponse<ReviewResDTO.MyReviewList> getMyReviewList(
            @RequestParam(name = "member_id") Long memberId,
            @RequestParam(name = "sort", defaultValue = "id") String sort,
            @RequestParam(required = false) String cursor,
            @RequestParam(required = false) Integer size
    ) {
        ReviewResDTO.MyReviewList result = reviewService.getMyReviewList(memberId, sort, cursor, size);
        BaseSuccessCode code = ReviewSuccessCode.REVIEW_LIST_OK;
        return ApiResponse.onSuccess(code, result);
    }
}

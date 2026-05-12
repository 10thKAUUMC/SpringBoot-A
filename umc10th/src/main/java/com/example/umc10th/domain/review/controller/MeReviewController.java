package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.res.ReviewResDTO;
import com.example.umc10th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "내 리뷰", description = "본인이 작성한 리뷰 목록(커서 페이징, 사진 URL 미포함)")
@RestController
@RequiredArgsConstructor
@RequestMapping("/me")
public class MeReviewController {

    private final ReviewService reviewService;

    @Operation(
            summary = "내가 쓴 리뷰 목록",
            description = """
                    해당 회원이 작성한 리뷰를 **커서 기반**으로 조회합니다. \
                    `sort=id`는 최신 리뷰(리뷰 ID 내림차순), `sort=star`는 별점 높은 순입니다."""
    )
    @GetMapping("/review-list")
    public ApiResponse<ReviewResDTO.MyReviewList> getMyReviewList(
            @Parameter(description = "회원 PK", required = true, example = "1")
            @RequestParam(name = "member_id") Long memberId,
            @Parameter(description = "`id` 또는 `star`", example = "id")
            @RequestParam(name = "sort", defaultValue = "id") String sort,
            @Parameter(description = "이전 응답의 `next_cursor`(첫 요청 시 생략)")
            @RequestParam(required = false) String cursor,
            @Parameter(description = "페이지당 건수(미지정 시 서버 기본값)")
            @RequestParam(required = false) Integer size
    ) {
        ReviewResDTO.MyReviewList result = reviewService.getMyReviewList(memberId, sort, cursor, size);
        BaseSuccessCode code = ReviewSuccessCode.REVIEW_LIST_OK;
        return ApiResponse.onSuccess(code, result);
    }
}

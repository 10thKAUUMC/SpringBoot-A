package com.example.umc10th.domain.review.controller;


import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.domain.Review;
import com.example.umc10th.domain.review.dto.ReviewResponseDTO;
import com.example.umc10th.domain.review.service.ReviewQueryService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class ReviewRestController { //API that shows the reviews categories from the storeId

    private final ReviewQueryService reviewQueryService;

    @GetMapping("/{storeId}/reviews")
    @Operation(summary = "특정 가게의 리뷰 목록 조회 API", description = "특정 가게의 리뷰 목록을 조회하는 API이며, 페이징을 포함합니다. query string으로 page 번호를 주세요.")
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다."),
            @Parameter(name = "page", description = "페이지 번호, 0번이 1페이지 입니다."),
    })
    public ApiResponse<ReviewResponseDTO.ReviewPreViewListDTO> getReviewList(
            @PathVariable(name = "storeId") Long storeId,
            @RequestParam(name = "page") Integer page
    ){
        //Call the Service
        Page<Review> reviewPage = reviewQueryService.getReviewList(storeId, page);

        //Convert it to DTO
        return ApiResponse.onSuccess(ReviewConverter.reviewPreViewListDTO(reviewPage));

    }

}

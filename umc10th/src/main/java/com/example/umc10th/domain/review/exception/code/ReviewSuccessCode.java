package com.example.umc10th.domain.review.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {

    REVIEW_CREATED(HttpStatus.OK, "REVIEW200_1", "성공적으로 리뷰를 작성하였습니다."),
    REVIEW_LIST_OK(HttpStatus.OK, "REVIEW200_2", "성공적으로 리뷰 목록을 조회했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}

package com.example.umc10th.global.apiPayload.exception;

import com.example.umc10th.global.apiPayload.code.GeneralErrorCode;
import lombok.Getter;

// 요청 본문({@code @RequestBody}) 검증 실패 시 사용합니다.
@Getter
public class RequestBodyValidationException extends GeneralException {

    private final String validationDetail;

    public RequestBodyValidationException(String validationDetail) {
        super(GeneralErrorCode.VALIDATION_FAILED);
        this.validationDetail = validationDetail;
    }
}

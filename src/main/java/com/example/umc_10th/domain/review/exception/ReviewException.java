package com.example.umc_10th.domain.review.exception;

import com.example.umc_10th.global.apiPayload.code.BaseErrorCode;
import com.example.umc_10th.global.apiPayload.exception.ProjectException;

public class ReviewException extends ProjectException {
    public ReviewException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}

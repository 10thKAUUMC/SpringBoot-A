package com.example.umc_10th.global.apiPayload.exception;

import com.example.umc_10th.global.apiPayload.ApiResponse;
import com.example.umc_10th.global.apiPayload.code.GeneralErrorCode;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralExceptionAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<?> handleValidationException(MethodArgumentNotValidException e) {

        String message = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst()
                .map(FieldError::getDefaultMessage)
                .orElse("잘못된 요청입니다.");

        return ApiResponse.onFailure(GeneralErrorCode.BAD_REQUEST, message);
    }
}

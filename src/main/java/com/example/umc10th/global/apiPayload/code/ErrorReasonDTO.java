package com.example.umc10th.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
@AllArgsConstructor
public class ErrorReasonDTO {
    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
    private Boolean isSuccess;
}

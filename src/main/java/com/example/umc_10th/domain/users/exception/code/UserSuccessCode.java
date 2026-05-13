package com.example.umc_10th.domain.users.exception.code;

import com.example.umc_10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserSuccessCode implements BaseSuccessCode {

    JOIN_SUCCESS(HttpStatus.CREATED, "USER201_1", "회원가입에 성공했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}

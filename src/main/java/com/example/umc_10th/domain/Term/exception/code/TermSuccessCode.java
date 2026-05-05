package com.example.umc_10th.domain.Term.exception.code;

import com.example.umc_10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum TermSuccessCode implements BaseSuccessCode {

    OK(HttpStatus.OK,
            "TERM200_1",
            "약관 조회 성공");

    private final HttpStatus status;
    private final String code;
    private final String message;

}

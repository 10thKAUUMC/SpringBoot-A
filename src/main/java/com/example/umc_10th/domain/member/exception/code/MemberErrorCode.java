package com.example.umc_10th.domain.member.exception.code;

import com.example.umc_10th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND,
            "MEMBER404_1",
            "해당 사용자를 찾지 못했습니다."),

    DUPLICATE_EMAIL(HttpStatus.CONFLICT,
            "MEMBER409_1",
                    "이미 가입된 이메일입니다."),

    INVALID_PASSWORD(HttpStatus.BAD_REQUEST,
            "MEMBER400_1",
                    "비밀번호가 요구사항을 만족하지 않습니다."),

    TERMS_NOT_AGREED(HttpStatus.BAD_REQUEST,
            "MEMBER400_2",
                    "필수 약관에 모두 동의해야 합니다."),

    TERM_NOT_FOUND(HttpStatus.NOT_FOUND,
            "MEMBER404_2",
                    "존재하지 않는 약관입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

}
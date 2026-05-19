package com.example.umc10th.domain.member.exeption.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {

    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER404_1", "해당 사용자를 찾을 수 없습니다."),
    INVALID_AGREEMENT(HttpStatus.BAD_REQUEST, "MEMBER400_1", "필수 약관에 동의해야 합니다."),
    INVALID_GENDER(HttpStatus.BAD_REQUEST, "MEMBER400_2", "성별 값이 올바르지 않습니다."),
    DUPLICATE_USERNAME(HttpStatus.CONFLICT, "MEMBER409_1", "이미 사용 중인 아이디입니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}

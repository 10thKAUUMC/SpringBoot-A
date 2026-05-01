package com.example.umc_10th.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GeneralSuccessCode implements BaseSuccessCode {

    // 데이터 저장 성공, 조회 성공 등 가장 많이 쓰일 코드입니다.
    OK(HttpStatus.OK, "COMMON200_1", "성공적으로 요청을 처리했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}

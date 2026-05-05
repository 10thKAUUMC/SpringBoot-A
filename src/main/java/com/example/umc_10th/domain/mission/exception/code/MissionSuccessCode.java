package com.example.umc_10th.domain.mission.exception.code;

import com.example.umc_10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {
    OK(HttpStatus.OK,
            "MISSION200_1",
            "성공적으로 미션을 조회했습니다."),

    MISSION_COMPLETED(HttpStatus.OK,
            "MISSION200_2",
                    "미션이 완료되었습니다.")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}

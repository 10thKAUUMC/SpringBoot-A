package com.example.umc_10th.domain.mission.dto;


import com.example.umc_10th.domain.mission.enums.MissionStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class MissionReqDTO {

    // 가게 미션 생성
    public record CreateMission(
            @NotNull(message = "미션 성공 포인트는 필수입니다.")
            Integer point,
            @NotBlank(message = "조건은 빈칸일 수 없습니다.")
            String title
    ) {}

    // 사용자 미션 목록 조회 (상태별)
    public record GetMissions(

            Long memberId,

            List<MissionStatus> status
    ) {}
    
}

package com.example.umc_10th.domain.users.dto;

import lombok.Builder;
import java.util.List;

public class UserResDTO {

    @Builder
    public record JoinResultDTO(
            Long id,
            String loginId,
            String userName
    ){}

    @Builder
    public record HomeResultDTO(
            String message
    ){}

    @Builder
    public record MyPageDTO(
            String userName,
            String email,
            String phoneNumber,
            Boolean isAuthenticated,
            Integer totalPoint
    ) {}

    @Builder
    public record HomeDTO(
            String userName,
            Integer totalPoint,
            Integer completedMissionCount,
            Integer totalMissionCount,
            List<HomeMissionDTO> missions
    ) {}

    @Builder
    public record HomeMissionDTO(
            Long missionId,
            String storeName,
            String missionTitle,
            Integer missionPoint,
            String missionSpec,
            Integer deadline
    ) {}

    @Builder
    public record LoginResultDTO(
            Long userId,
            String email,
            String accessToken
    ) {}
}

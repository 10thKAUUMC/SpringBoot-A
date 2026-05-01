package com.example.umc_10th.domain.users.dto;

import lombok.Builder;

public class UserResDTO {

    @Builder
    public record JoinResultDTO(
            Long id,
            String userId,
            String userName
    ){}

    @Builder
    public record HomeResultDTO(
            String message
    ){}
}

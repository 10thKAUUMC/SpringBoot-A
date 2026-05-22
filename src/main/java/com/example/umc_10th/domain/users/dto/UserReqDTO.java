package com.example.umc_10th.domain.users.dto;
import jakarta.validation.constraints.NotNull;

public class UserReqDTO {

    public record JoinDTO(
            String userName,
            String phoneNumber,
            String loginId,
            String userPassword
    ){}

    public record HomeRequestDTO(
            @NotNull(message = "사용자 ID는 필수입니다.")
            Long memberId
    ) {}
}

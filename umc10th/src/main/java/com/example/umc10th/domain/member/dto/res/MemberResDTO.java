package com.example.umc10th.domain.member.dto.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

public class MemberResDTO {

    @Builder
    public record GetInfo(
            Long memberId,
            String name,
            String profileUrl,
            String email,
            String gender,
            String birthDate,
            String address,
            String detailAddress,
            String phoneNumber,
            Integer point
    ) {
    }

    @Builder
    public record Signup(
            int status,
            String message,
            SignupData data
    ) {
    }

    @Builder
    public record SignupData(
            @JsonProperty("userId")
            Long userId
    ) {
    }

    @Builder
    public record Login(
            int status,
            String message,
            LoginData data
    ) {
    }

    @Builder
    public record LoginData(
            @JsonProperty("accessToken")
            String accessToken,
            @JsonProperty("tokenType")
            String tokenType,
            @JsonProperty("expiresIn")
            Long expiresIn
    ) {
    }
}

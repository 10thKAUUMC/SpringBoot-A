package com.example.umc10th.domain.member.dto.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

public class MemberResDTO {

    @Builder
    public record GetInfo(
            String name,
            String profileUrl,
            String email,
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
}

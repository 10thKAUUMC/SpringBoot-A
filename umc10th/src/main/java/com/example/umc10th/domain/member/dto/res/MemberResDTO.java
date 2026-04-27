package com.example.umc10th.domain.member.dto.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

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
            @JsonProperty("user_name")
            String userName,
            String gender,
            LocalDate birthday,
            String address,
            @JsonProperty("detail_address")
            String detailAddress,
            String email,
            String phone,
            List<String> category,
            String message
    ) {
    }
}

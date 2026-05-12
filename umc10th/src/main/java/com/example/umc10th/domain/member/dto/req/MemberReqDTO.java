package com.example.umc10th.domain.member.dto.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {

    // 마이페이지
    public record GetInfo(
            @NotNull
            Long id
    ) {
    }

    public record Signup(
            @JsonProperty("user_name")
            @NotBlank
            String userName,
            @NotBlank
            String gender,
            @NotNull
            LocalDate birthday,
            @NotBlank
            String address,
            @JsonProperty("detail_address")
            @NotBlank
            String detailAddress,
            @NotBlank
            @Email
            String email,
            @NotBlank
            String phone,
            List<String> category
    ) {
    }
}

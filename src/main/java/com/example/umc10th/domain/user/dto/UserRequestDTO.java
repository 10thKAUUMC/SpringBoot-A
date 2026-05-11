package com.example.umc10th.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class UserRequestDTO {

    public record JoinDTO(
        @NotNull String nickname,
        @Email String email,
        @NotBlank String password,
        @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}") String phone,
        String birth,
        String gender,
        String detail_address
    ) {}


    public record PostReviewDTO (
        Long store_id,
        Integer rating,
        String content
    ) {}

    public record MemberMissionQueryDTO(
            @NotNull Long memberId,
            @NotNull Integer page
    ) {}


}

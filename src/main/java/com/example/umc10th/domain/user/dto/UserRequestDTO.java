package com.example.umc10th.domain.user.dto;

import jakarta.validation.constraints.*;
import java.util.List;

public class UserRequestDTO {

    public record JoinDTO(
        @NotBlank String nickname,
        @Email @NotBlank String email,
        @NotBlank String password, //Encrypted

        @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}")
        String phone,
        String birth,
        String gender,
        String detail_address,
        List<Long> preferCategory
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

package com.example.umc_10th.domain.users.dto;
import jakarta.validation.constraints.NotNull;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserReqDTO {

    public record JoinDTO(
            @NotBlank(message = "이름은 필수입니다.")
            String userName,

            @NotBlank(message = "전화번호는 필수입니다.")
            String phoneNumber,

            @NotBlank(message = "로그인 ID는 필수입니다.")
            String loginId,

            @NotBlank(message = "이메일은 필수입니다.")
            @Email(message = "이메일 형식이 올바르지 않습니다.")
            String email,

            @NotBlank(message = "비밀번호는 필수입니다.")
            String password,

            String gender,

            String birth,

            String address
    ){}

    public record LoginDTO(
            @NotBlank(message = "이메일은 필수입니다.")
            @Email(message = "이메일 형식이 올바르지 않습니다.")
            String email,

            @NotBlank(message = "비밀번호는 필수입니다.")
            String password
    ) {}

    public record HomeRequestDTO(
            @NotNull(message = "사용자 ID는 필수입니다.")
            Long memberId
    ) {}
}

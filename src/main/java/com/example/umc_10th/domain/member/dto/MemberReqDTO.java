package com.example.umc_10th.domain.member.dto;

import com.example.umc_10th.domain.member.enums.FoodName;
import com.example.umc_10th.domain.member.enums.Gender;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {

    public record GetInfo(
            Long id
    ){}

    // 약관 동의 정보
    public record TermAgreement(
            @NotNull(message = "약관 ID는 필수입니다.")
            Long termId,

            @NotNull(message = "동의 여부는 필수입니다.")
            Boolean agreed
    ) {}

    // 회원가입 요청 DTO
    public record Signup(
            @NotBlank(message = "이름은 필수입니다.")
            @Size(min = 2, max = 50, message = "이름은 2자 이상 50자 이하여야 합니다.")
            String name,

            @NotBlank(message = "이메일은 필수입니다.")
            @Email(message = "유효하지 않은 이메일 형식입니다.")
            String email,

            @NotBlank(message = "비밀번호는 필수입니다.")
            @Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하여야 합니다.")
            @Pattern(
                    regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$",
                    message = "비밀번호는 영문, 숫자, 특수문자(@$!%*?&)를 포함해야 합니다."
            )
            String password,

            @NotNull(message = "성별은 필수입니다.")
            Gender gender,

            @NotNull(message = "생년월일은 필수입니다.")
            @PastOrPresent(message = "생년월일은 현재 시간보다 이전이어야 합니다.")
            LocalDate birth,

            @NotBlank(message = "주소는 필수입니다.")
            String address,

            @NotBlank(message = "상세 주소는 필수입니다.")
            String detailedAddress,


            List<FoodName> preferredFoods,

            @NotNull(message = "약관 동의 정보는 필수입니다.")
            @Valid
            List<TermAgreement> agreedTerms
    ) {}

    // 로그인 요청 DTO
    public record Login(
            @NotBlank(message = "이메일은 필수입니다.")
            @Email(message = "유효하지 않은 이메일 형식입니다.")
            String email,

            @NotBlank(message = "비밀번호는 필수입니다.")
            String password
    ) {}
}

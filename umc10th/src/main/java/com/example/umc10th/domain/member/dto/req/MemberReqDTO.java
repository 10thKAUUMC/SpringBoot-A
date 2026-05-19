package com.example.umc10th.domain.member.dto.req;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.Valid;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {

    public record Signup(
            @NotBlank
            @Email
            String username,
            @NotBlank
            @Size(min = 8, max = 100)
            String password,
            @NotNull
            @Valid
            Agreements agreements,
            @NotNull
            @Valid
            Profile profile,
            List<String> favoriteFoodCategories
    ) {
    }

    public record Login(
            @NotBlank
            @Email
            String username,
            @NotBlank
            String password
    ) {
    }

    public record Agreements(
            @NotNull
            Boolean isAgeOver14,
            @NotNull
            Boolean termsOfService,
            @NotNull
            Boolean privacyPolicy,
            Boolean location,
            Boolean marketing
    ) {
    }

    public record Profile(
            @NotBlank
            String name,
            @NotBlank
            String gender,
            @NotNull
            LocalDate birthDate,
            @NotBlank
            String address,
            String detailAddress
    ) {
    }
}

package com.example.umc10th.domain.member.dto.req;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {

    // 마이페이지
    public record GetInfo(
            Long id
    ) {
    }

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
            List<String> category
    ) {
    }
}

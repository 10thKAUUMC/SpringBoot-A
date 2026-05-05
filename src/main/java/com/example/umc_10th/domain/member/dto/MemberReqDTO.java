package com.example.umc_10th.domain.member.dto;

public class MemberReqDTO {

    public record GetInfo(
            Long id
    ){}

    // 회원가입
    public record Signup(
            String name,
            String gender,
            String birth,
            String address,
            String detailedAddress
    ){}


}

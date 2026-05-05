package com.example.umc_10th.domain.member.dto;

import lombok.Builder;

public class MemberResDTO {

    //마이페이지
    @Builder
    public record GetInfo(
            String name,
            String email,
            String phoneNumber,
            Integer point
    ){}

    //사용자 지역 조회
    @Builder
    public record GetLocation(
            String location
    ){}

    //사용자 포인트 조회
    @Builder
    public record GetPoint(
            Integer point
    ){}

    //회원가입
    @Builder
    public record Signup(
            Long memberId,
            String name
    ){}

}

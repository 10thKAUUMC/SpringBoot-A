package com.example.umc_10th.domain.member.dto;

import com.example.umc_10th.domain.mission.dto.MissionResDTO;
import lombok.Builder;

import java.util.List;

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

    @Builder
    public record Home(
            GetLocation location,
            GetPoint points,
            List<MissionResDTO.GetNearby> missions
    ) {}

    //회원가입
    @Builder
    public record Signup(
            Long memberId,
            String name
    ){}

}

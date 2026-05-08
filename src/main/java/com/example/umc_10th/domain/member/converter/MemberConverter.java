package com.example.umc_10th.domain.member.converter;

import com.example.umc_10th.domain.member.dto.MemberResDTO;
import com.example.umc_10th.domain.member.entity.Member;
import com.example.umc_10th.domain.mission.dto.MissionResDTO;
import com.example.umc_10th.domain.mission.entity.Mission;

public class MemberConverter {

    public static MemberResDTO.GetInfo toGetInfo(
            Member member
    ){
        return MemberResDTO.GetInfo.builder()
                .email(member.getEmail())
                .name(member.getName())
                .phoneNumber(member.getPhoneNumber())
                .point(member.getPoint())
                .build();
    }

    // Mission 데이터를 GetNearby DTO로 변환
    public static MissionResDTO.GetNearby toNearbyMissionDTO(Mission mission) {
        return MissionResDTO.GetNearby.builder()
                .storeName(mission.getStore().getName())
                .missionTitle(mission.getTitle())
                .rewardPoint(mission.getPoint())
                .build();
    }
}

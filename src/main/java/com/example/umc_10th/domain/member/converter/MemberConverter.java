package com.example.umc_10th.domain.member.converter;

import com.example.umc_10th.domain.member.dto.MemberResDTO;
import com.example.umc_10th.domain.member.entity.Member;

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
}

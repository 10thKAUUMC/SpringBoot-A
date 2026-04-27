package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.dto.res.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;

public class MemberConverter {

    // 마이페이지
    public static MemberResDTO.GetInfo toGetInfo(
            Member member
    ) {
        return MemberResDTO.GetInfo.builder()
                .email(member.getEmail())
                .name(member.getName())
                .point(member.getPoint())
                .phoneNumber(member.getPhoneNumber())
                .profileUrl(member.getProfileUrl())
                .build();
    }
}

package com.example.umc_10th.domain.member.service;

import com.example.umc_10th.domain.member.converter.MemberConverter;
import com.example.umc_10th.domain.member.dto.MemberReqDTO;
import com.example.umc_10th.domain.member.dto.MemberResDTO;
import com.example.umc_10th.domain.member.entity.Member;
import com.example.umc_10th.domain.member.exception.MemberException;
import com.example.umc_10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc_10th.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    //마이페이지
    public MemberResDTO.GetInfo getInfo(
            MemberReqDTO.GetInfo dto
    ) {
        Long memberId = dto.id();
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()-> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
        return MemberConverter.toGetInfo(member);
    }
}

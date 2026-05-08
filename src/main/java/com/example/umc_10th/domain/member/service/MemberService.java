package com.example.umc_10th.domain.member.service;

import com.example.umc_10th.domain.member.converter.MemberConverter;
import com.example.umc_10th.domain.member.dto.MemberReqDTO;
import com.example.umc_10th.domain.member.dto.MemberResDTO;
import com.example.umc_10th.domain.member.entity.Member;
import com.example.umc_10th.domain.member.exception.MemberException;
import com.example.umc_10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc_10th.domain.member.repository.MemberRepository;
import com.example.umc_10th.domain.mission.converter.MissionConverter;
import com.example.umc_10th.domain.mission.dto.MissionResDTO;
import com.example.umc_10th.domain.mission.enums.MissionStatus;
import com.example.umc_10th.domain.mission.repository.MemberMissionRepository;
import com.example.umc_10th.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;


    // 마이페이지
    public MemberResDTO.GetInfo getInfo(
            MemberReqDTO.GetInfo dto
    ) {
        Long memberId = dto.id();
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()-> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
        return MemberConverter.toGetInfo(member);
    }

    // 홈 화면 생성
    public MemberResDTO.Home getHomeData(Long memberId) {
        // 사용자 조회
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("사용자 정보를 찾을 수 없습니다."));

        // 지역정보 DTO 변환
        MemberResDTO.GetLocation location = MemberResDTO.GetLocation.builder()
                .location(member.getAddress())
                .build();

        // 포인트 정보 DTO 변환
        MemberResDTO.GetPoint points = MemberResDTO.GetPoint.builder()
                .point(member.getPoint())
                .build();

        // 사용자 지역 기반 도전 가능한 미션 조회 및 변환
        List<MissionResDTO.GetNearby> missions = memberMissionRepository.findByMemberAndLocationAndStatus(
                        memberId, member.getAddress(), MissionStatus.NOT_STARTED
                ).stream()
                .map(MissionConverter::toNearbyMissionDTO) // Converter 사용
                .collect(Collectors.toList());

        // 통합 결과 반환
        return new MemberResDTO.Home(location, points, missions);
    }
}

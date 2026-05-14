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
import com.example.umc_10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc_10th.domain.mission.enums.MissionStatus;
import com.example.umc_10th.domain.mission.repository.MemberMissionRepository;
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

    // 홈 화면 데이터 생성
    public MemberResDTO.Home getHomeData(Long memberId) {
        // 사용자 데이터 로드
        Member member = getMember(memberId);

        // 사용자 지역 DTO 생성
        MemberResDTO.GetLocation location = MemberConverter.toLocationDTO(member.getLocation());

        // 사용자 포인트 DTO 생성
        MemberResDTO.GetPoint points = MemberConverter.toPointDTO(member.getPoint());

        // 사용자 지역 기반 시작 전 상태 미션 데이터 생성
        List<MissionResDTO.GetNearby> missions = getNearbyMissions(member);

        // 통합 DTO 반환
        return new MemberResDTO.Home(location, points, missions);
    }

    // 사용자 데이터 조회
    private Member getMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
    }

    // 지역 기반 시작 전 상태 미션 목록 조회
    private List<MissionResDTO.GetNearby> getNearbyMissions(Member member) {

        return memberMissionRepository.findHomeMissions(
                        member.getId(),
                        member.getLocation(),
                        MissionStatus.NOT_STARTED
                ).stream()
                .map(MissionConverter::toNearbyMissionDTO)
                .collect(Collectors.toList());
    }
}

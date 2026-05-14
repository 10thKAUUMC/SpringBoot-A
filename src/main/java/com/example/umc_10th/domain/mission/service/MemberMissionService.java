package com.example.umc_10th.domain.mission.service;

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
public class MemberMissionService {

    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;

    // 사용자 미션 조회 (상태별)
    public List<MissionResDTO.GetUserMissions> getUserMissions(Long memberId, List<MissionStatus> status) {
        // 사용자 조회
        memberRepository.findById(memberId)
                .orElseThrow(() ->
                        new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        // 사용자 미션 조회
        List<MemberMission> memberMissions = memberMissionRepository.findUserMissions(memberId, status);

        // DTO 변환
        return memberMissions.stream()
                .map(MissionConverter::toUserMissionDTO)
                .collect(Collectors.toList());
    }
}

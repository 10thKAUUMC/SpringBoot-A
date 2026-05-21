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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberMissionService {

    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;

    // 사용자 미션 조회 (상태별)
    public MissionResDTO.OffsetPagenation<MissionResDTO.GetUserMissions> getUserMissions(
            Long memberId,
            List<MissionStatus> status,
            Integer pageSize,
            Integer pageNumber,
            String sort) {
        // 정렬 정보 생성
        Sort sortInfo;
        if (sort != null){
            sortInfo = Sort.by(sort);
        } else {
            sortInfo = Sort.by("id").descending();
        }

        // 페이지 정보들을 PageRequest로 만들기
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortInfo);

        // 사용자 조회
        memberRepository.findById(memberId)
                .orElseThrow(() ->
                        new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        // 사용자 미션 조회
        Page<MemberMission> memberMissions = memberMissionRepository.findUserMissions(memberId, status, pageRequest);

        // DTO 변환
        return MissionConverter.toOffsetPagination(
                memberMissions.map(MissionConverter::toUserMissionDTO).toList(),
                memberMissions.getNumber(),
                memberMissions.getSize()
                );
    }
}

package com.example.umc_10th.domain.mission.service;

import com.example.umc_10th.domain.mission.converter.MissionConverter;
import com.example.umc_10th.domain.mission.dto.MissionResDTO;
import com.example.umc_10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc_10th.domain.mission.repository.MemberMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberMissionService {

    private final MemberMissionRepository memberMissionRepository;

    public List<MissionResDTO.GetUserMissions> getInProgressOrCompletedMissions(Long memberId) {
        List<MemberMission> memberMissions = memberMissionRepository.findInProgressOrCompletedMissions(memberId);
        return memberMissions.stream()
                .map(MissionConverter::toUserMissionDTO)
                .collect(Collectors.toList());
    }
}

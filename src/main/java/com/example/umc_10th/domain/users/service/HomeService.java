package com.example.umc_10th.domain.users.service;

import com.example.umc_10th.domain.membermission.repository.MemberMissionRepository;
import com.example.umc_10th.domain.mission.entity.Mission;
import com.example.umc_10th.domain.mission.repository.MissionRepository;
import com.example.umc_10th.domain.users.dto.UserResDTO;
import com.example.umc_10th.domain.users.entity.User;
import com.example.umc_10th.domain.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HomeService {

    private final UserRepository userRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    public UserResDTO.HomeDTO getHome(Long memberId, Long regionId, Integer page) {

        User user = userRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("회원을 찾을 수 없습니다."));

        Page<Mission> missions = missionRepository.findHomeMissionsByRegionId(
                regionId,
                PageRequest.of(page, 10)
        );

        return UserResDTO.HomeDTO.builder()
                .userName(user.getUserName())
                .totalPoint(user.getTotalPoint())
                .completedMissionCount(7)
                .totalMissionCount(10)
                .missions(
                        missions.stream()
                                .map(mission -> UserResDTO.HomeMissionDTO.builder()
                                        .missionId(mission.getId())
                                        .storeName(mission.getStore().getStoreName())
                                        .missionTitle(mission.getMissionTitle())
                                        .missionPoint(mission.getMissionPoint())
                                        .missionSpec(mission.getMissionSpec())
                                        .deadline(mission.getDeadline())
                                        .build())
                                .toList()
                )
                .build();
    }
}
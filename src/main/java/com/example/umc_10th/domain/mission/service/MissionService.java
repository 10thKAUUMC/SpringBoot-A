package com.example.umc_10th.domain.mission.service;

import com.example.umc_10th.domain.membermission.entity.MemberMission;
import com.example.umc_10th.domain.membermission.repository.MemberMissionRepository;
import com.example.umc_10th.domain.mission.dto.MissionReqDTO;
import com.example.umc_10th.domain.mission.dto.MissionResDTO;
import com.example.umc_10th.domain.mission.entity.Mission;
import com.example.umc_10th.domain.mission.enums.MissionStatus;
import com.example.umc_10th.domain.users.entity.User;
import com.example.umc_10th.domain.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MemberMissionRepository memberMissionRepository;
    private final UserRepository userRepository;

    public MissionResDTO.MissionListDTO getMissions(MissionStatus status, Integer page) {

        User user = userRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("회원을 찾을 수 없습니다."));

        Page<MemberMission> memberMissions =
                memberMissionRepository.findMissionListByUserAndStatus(
                        user,
                        status,
                        PageRequest.of(page, 10)
                );

        return MissionResDTO.MissionListDTO.builder()
                .missions(
                        memberMissions.stream()
                                .map(memberMission -> {
                                    Mission mission = memberMission.getMission();

                                    return MissionResDTO.MissionDTO.builder()
                                            .missionId(mission.getId())
                                            .storeId(mission.getStore().getId())
                                            .missionTitle(mission.getMissionTitle())
                                            .missionPoint(mission.getMissionPoint())
                                            .missionSpec(mission.getMissionSpec())
                                            .deadline(mission.getDeadline())
                                            .build();
                                })
                                .toList()
                )
                .build();
    }

    public MissionResDTO.SuccessResultDTO successMission(Long missionId, MissionReqDTO.SuccessDTO request) {

        return MissionResDTO.SuccessResultDTO.builder()
                .missionId(missionId)
                .state(request.state())
                .build();
    }
}



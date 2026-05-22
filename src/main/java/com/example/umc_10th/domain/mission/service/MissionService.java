package com.example.umc_10th.domain.mission.service;

import com.example.umc_10th.domain.membermission.entity.MemberMission;
import com.example.umc_10th.domain.membermission.repository.MemberMissionRepository;
import com.example.umc_10th.domain.mission.dto.MissionResDTO;
import com.example.umc_10th.domain.mission.entity.Mission;
import com.example.umc_10th.domain.mission.enums.MissionStatus;
import com.example.umc_10th.domain.users.entity.User;
import com.example.umc_10th.domain.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MemberMissionRepository memberMissionRepository;
    private final UserRepository userRepository;

    public MissionResDTO.MissionListDTO getMissions(Long memberId, MissionStatus status, Integer page) {

        User user = userRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("회원을 찾을 수 없습니다."));

        Page<MemberMission> memberMissions =
                memberMissionRepository.findMissionListByUserAndStatus(
                        user,
                        status,
                        PageRequest.of(page, 10)
                );

        return convertToMissionListDTO(memberMissions);
    }

    private MissionResDTO.MissionListDTO convertToMissionListDTO(Page<MemberMission> memberMissions) {

        List<MissionResDTO.MissionDTO> missionDTOList = memberMissions.stream()
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
                .toList();

        return MissionResDTO.MissionListDTO.builder()
                .missions(missionDTOList)
                .listSize(missionDTOList.size())
                .totalPage(memberMissions.getTotalPages())
                .totalElements(memberMissions.getTotalElements())
                .isFirst(memberMissions.isFirst())
                .isLast(memberMissions.isLast())
                .build();
    }

    public MissionResDTO.SuccessResultDTO successMission(Long missionId) {
        return MissionResDTO.SuccessResultDTO.builder()
                .missionId(missionId)
                .state(MissionStatus.COMPLETED)
                .build();
    }

    // 7주차 미션 메서드 추가됨: 내가 진행 중인 미션 조회
    public MissionResDTO.MissionListDTO getMyProgressMissions(Long memberId, Integer page) {

        User user = userRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("회원을 찾을 수 없습니다."));

        Page<MemberMission> memberMissions =
                memberMissionRepository.findMissionListByUserAndStatus(
                        user,
                        MissionStatus.IN_PROGRESS,
                        PageRequest.of(page, 10)
                );

        return convertToMissionListDTO(memberMissions);
    }
}



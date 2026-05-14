package com.example.umc_10th.domain.mission.converter;

import com.example.umc_10th.domain.mission.dto.MissionResDTO;
import com.example.umc_10th.domain.mission.entity.mapping.MemberMission;

public class MissionConverter {

    // 사용자 미션 목록 조회 (상태별)
    public static MissionResDTO.GetUserMission toUserMissionDTO(MemberMission memberMission) {
        return MissionResDTO.GetUserMission.builder()
                .storeName(memberMission.getMission().getStore().getName())
                .title(memberMission.getMission().getTitle())
                .point(memberMission.getMission().getPoint())
                .status(memberMission.getMissionStatus())
                .dueDate(memberMission.getDueDate())
                .build();
    }

    // 도전 가능한 미션을 변환
    public static MissionResDTO.GetNearby toNearbyMissionDTO(MemberMission memberMission) {
        return MissionResDTO.GetNearby.builder()
                .storeName(memberMission.getMission().getStore().getName())
                .missionTitle(memberMission.getMission().getTitle())
                .rewardPoint(memberMission.getMission().getPoint())
                .build();
    }
}

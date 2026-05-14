package com.example.umc_10th.domain.mission.converter;

import com.example.umc_10th.domain.mission.dto.MissionReqDTO;
import com.example.umc_10th.domain.mission.dto.MissionResDTO;
import com.example.umc_10th.domain.mission.entity.Mission;
import com.example.umc_10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc_10th.domain.store.entity.Store;

import java.util.List;

public class MissionConverter {

    // 사용자 미션 목록 조회 (상태별)
    public static MissionResDTO.GetUserMissions toUserMissionDTO(MemberMission memberMission) {
        return MissionResDTO.GetUserMissions.builder()
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

    // 가게 미션 생성
    public static Mission toMission(Store store, MissionReqDTO.CreateMission dto) {
        return Mission.builder()
                .store(store)
                .title(dto.title())
                .point(dto.point())
                .build();
    }

    // 가게 내 미션 조회
    public static MissionResDTO.GetStoreMissions toGetMission(
            Mission mission
    ){
        return MissionResDTO.GetStoreMissions.builder()
                .missionId(mission.getId())
                .point(mission.getPoint())
                .title(mission.getTitle())
                .build();
    }

    // 페이지네이션 틀 생성
    public static <T> MissionResDTO.Pagenation<T> toPagination(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ){
        return MissionResDTO.Pagenation.<T>builder()
                .data(data)
                .hasNext(hasNext)
                .nextCursor(nextCursor)
                .pageSize(pageSize)
                .build();
    }
}

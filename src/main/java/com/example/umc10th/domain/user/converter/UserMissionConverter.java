package com.example.umc10th.domain.user.converter;

import com.example.umc10th.domain.mission.domain.UserMission;
import com.example.umc10th.domain.user.dto.UserResponseDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class UserMissionConverter {


    public static UserResponseDTO.UserMissionPreViewDTO userMissionPreViewDTO(UserMission userMission) {
        return UserResponseDTO.UserMissionPreViewDTO.builder()
                .missionId(userMission.getMission().getId())
                .storeName(userMission.getMission().getStore().getName()) // Fetch Join 덕분에 안전함
                .missionSpec(userMission.getMission().getMissionSpec())
                .reward(userMission.getMission().getReward())
                .status(userMission.getStatus().name())
                .createdAt(userMission.getCreatedAt())
                .build();
    }


    public static UserResponseDTO.UserMissionPreViewListDTO userMissionPreViewListDTO(Page<UserMission> userMissionPage) {

        List<UserResponseDTO.UserMissionPreViewDTO> userMissionPreViewDTOList = userMissionPage.stream()
                .map(UserMissionConverter::userMissionPreViewDTO)
                .collect(Collectors.toList());

        return UserResponseDTO.UserMissionPreViewListDTO.builder()
                .isLast(userMissionPage.isLast())
                .isFirst(userMissionPage.isFirst())
                .totalPage(userMissionPage.getTotalPages())
                .totalElements(userMissionPage.getTotalElements())
                .listSize(userMissionPreViewDTOList.size())
                .missionList(userMissionPreViewDTOList)
                .build();
    }
}
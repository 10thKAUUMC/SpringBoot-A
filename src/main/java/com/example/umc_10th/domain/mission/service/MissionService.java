package com.example.umc_10th.domain.mission.service;

import com.example.umc_10th.domain.mission.dto.MissionResDTO;
import com.example.umc_10th.domain.mission.enums.MissionStatus;
import org.springframework.stereotype.Service;
import com.example.umc_10th.domain.mission.dto.MissionReqDTO;
import java.util.List;

@Service
public class MissionService {

    public MissionResDTO.MissionListDTO getMissions(MissionStatus status) {

        return MissionResDTO.MissionListDTO.builder()
                .missions(List.of(
                        MissionResDTO.MissionDTO.builder()
                                .missionId(1L)
                                .storeId(23L)
                                .missionTitle("리뷰 작성하기")
                                .missionPoint(500)
                                .missionSpec("가게 리뷰를 작성하면 포인트를 지급합니다.")
                                .deadline(7)
                                .build()
                ))
                .build();
    }

    public MissionResDTO.SuccessResultDTO successMission(Long missionId, MissionReqDTO.SuccessDTO request) {

        return MissionResDTO.SuccessResultDTO.builder()
                .missionId(missionId)
                .state(request.state())
                .build();
    }
}



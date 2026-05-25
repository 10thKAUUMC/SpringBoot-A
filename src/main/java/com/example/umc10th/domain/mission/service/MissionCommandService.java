package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.dao.UserMissionRepository;
import com.example.umc10th.domain.mission.domain.MissionStatus;
import com.example.umc10th.domain.mission.domain.UserMission;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionCommandService {

    private final UserMissionRepository userMissionRepository;

    public UserMission completeMission(Long userMissionId) {
        // 1. Repository에서 해당 UserMission을 찾음
        UserMission userMission = userMissionRepository.findById(userMissionId)
                .orElseThrow(() -> new RuntimeException("해당하는 사용자 미션을 찾을 수 없습니다."));

        // 2. 상태 변경 (Dirty Checking으로 자동 업데이트)
        userMission.setStatus(MissionStatus.COMPLETE);

        return userMission;
    }
}
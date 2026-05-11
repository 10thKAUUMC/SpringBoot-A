package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.domain.MissionStatus;
import com.example.umc10th.domain.mission.domain.UserMission;
import com.example.umc10th.domain.mission.dao.UserMissionRepository;
import com.example.umc10th.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) //Only looking
public class UserMissionQueryService {

    private final UserMissionRepository userMissionRepository;

    public Page<UserMission> getMissions(Long userId, MissionStatus status, Integer page) {
        //Brings specific user's status(ongoing or completed)
        int pageNumber = (page != null && page > 0) ? page - 1 : 0;
        Pageable pageable = PageRequest.of(pageNumber, 10);
        return userMissionRepository.findAllByUserIdAndStatus(userId, status, pageable);

    }


}

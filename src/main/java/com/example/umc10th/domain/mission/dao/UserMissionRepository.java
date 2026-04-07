package com.example.umc10th.domain.mission.dao;

import com.example.umc10th.domain.mission.domain.UserMission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMissionRepository extends JpaRepository<UserMission, Long> {
    //This is very useful when you're trying to find specific user on ongoing mission
    //List<UserMission> findAllByUserId(Long userId);
}

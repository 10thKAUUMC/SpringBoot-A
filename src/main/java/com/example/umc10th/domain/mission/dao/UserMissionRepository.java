package com.example.umc10th.domain.mission.dao;

import com.example.umc10th.domain.mission.domain.MissionStatus;
import com.example.umc10th.domain.mission.domain.UserMission;
import com.example.umc10th.domain.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMissionRepository extends JpaRepository<UserMission, Long> {
    //This is very useful when you're trying to find specific user on ongoing mission
    //List<UserMission> findAllByUserId(Long userId);

    @Query(value = "SELECT um FROM UserMission um JOIN FETCH um.mission m JOIN FETCH m.store WHERE um.user = :user AND um.status = :status",
            countQuery = "SELECT count(um) FROM UserMission um WHERE um.user =:user AND um.status = :status") //Checks for paging error
    Page<UserMission> findAllByUserIdAndStatus(@Param("userId") Long userId, @Param("status") MissionStatus status, Pageable pageable);
}

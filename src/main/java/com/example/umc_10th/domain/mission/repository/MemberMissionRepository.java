package com.example.umc_10th.domain.mission.repository;

import com.example.umc_10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc_10th.domain.mission.enums.MissionStatus;
import com.example.umc_10th.domain.store.entity.Location;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    // 사용자 미션 조회 (상태별)
    @Query("SELECT mm FROM MemberMission mm " +
           "WHERE mm.member.id = :memberId AND (mm.missionStatus IN :status)")
    Page<MemberMission> findUserMissions(
            @Param("memberId") Long memberId,
            @Param("status") List<MissionStatus> status,
            Pageable pageable);

    // 특정 사용자와 지역 기반 시작 전 상태의 미션 조회
    @Query("""
    SELECT mm
    FROM MemberMission mm
    JOIN FETCH mm.mission m
    JOIN FETCH m.store s
    JOIN FETCH s.location l
    WHERE mm.member.id = :memberId
      AND l = :location
      AND mm.missionStatus = :status
""")
    List<MemberMission> findHomeMissions(
            @Param("memberId") Long memberId,
            @Param("location") Location location,
            @Param("status") MissionStatus status
    );
}

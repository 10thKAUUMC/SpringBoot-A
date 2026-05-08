package com.example.umc_10th.domain.mission.repository;

import com.example.umc_10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc_10th.domain.mission.enums.MissionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    // 진행 중 또는 완료된 미션만 조회
    @Query("SELECT mm FROM MemberMission mm " +
           "JOIN FETCH mm.mission m " +
           "WHERE mm.member.id = :memberId AND (mm.missionStatus = 'IN_PROGRESS' OR mm.missionStatus = 'COMPLETED')")
    List<MemberMission> findInProgressOrCompletedMissions(@Param("memberId") Long memberId);

    // 특정 사용자와 지역 기반 시작 전 상태의 미션 조회
    @Query("SELECT mm FROM MemberMission mm " +
            "JOIN FETCH mm.mission m " +
            "JOIN FETCH m.store s " +
            "WHERE mm.member.id = :memberId AND s.location = :location AND mm.missionStatus = :status")
    List<MemberMission> findByMemberAndLocationAndStatus(
            @Param("memberId") Long memberId,
            @Param("location") String location,
            @Param("status") MissionStatus status
    );
}

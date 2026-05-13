package com.example.umc_10th.domain.membermission.repository;

import com.example.umc_10th.domain.membermission.entity.MemberMission;
import com.example.umc_10th.domain.mission.enums.MissionStatus;
import com.example.umc_10th.domain.users.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    @Query(
            value = """
                select mm
                from MemberMission mm
                join fetch mm.mission m
                join fetch m.store s
                where mm.user = :user
                  and mm.state = :status
                """,
            countQuery = """
                select count(mm)
                from MemberMission mm
                where mm.user = :user
                  and mm.state = :status
                """
    )
    Page<MemberMission> findMissionListByUserAndStatus(
            User user,
            MissionStatus status,
            Pageable pageable
    );
}

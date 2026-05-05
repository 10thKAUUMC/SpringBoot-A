package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.repository.projection.HomeMissionCursorRow;
import com.example.umc10th.domain.mission.repository.projection.MissionListCursorRow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    Optional<MemberMission> findByMember_MemberIdAndMission_Id(Long memberId, Long missionId);

    @Query(value = """
            SELECT
                COUNT(*)
            FROM member_mission um
            JOIN mission m ON um.mission_id = m.mission_id
            JOIN store s ON m.store_id = s.store_id
            JOIN location l ON s.location_id = l.location_id
            WHERE um.member_id = :memberId
              AND l.name = :locationName
            """, nativeQuery = true)
    long countByMemberAndLocationName(@Param("memberId") Long memberId,
                                      @Param("locationName") String locationName);

    @Query(value = """
            SELECT
                COUNT(*)
            FROM member_mission um
            JOIN mission m ON um.mission_id = m.mission_id
            JOIN store s ON m.store_id = s.store_id
            JOIN location l ON s.location_id = l.location_id
            WHERE um.member_id = :memberId
              AND l.name = :locationName
              AND um.is_complete IS TRUE
            """, nativeQuery = true)
    long countCompletedByMemberAndLocationName(@Param("memberId") Long memberId,
                                               @Param("locationName") String locationName);

    @Query(value = """
            SELECT
                COALESCE(SUM(m.point), 0)
            FROM member_mission um
            JOIN mission m ON um.mission_id = m.mission_id
            JOIN store s ON m.store_id = s.store_id
            JOIN location l ON s.location_id = l.location_id
            WHERE um.member_id = :memberId
              AND l.name = :locationName
              AND um.is_complete IS TRUE
            """, nativeQuery = true)
    int sumMissionPointsCompletedByMemberAndLocationName(@Param("memberId") Long memberId,
                                                         @Param("locationName") String locationName);

    @Query(value = """
            SELECT
                s.store_id AS storeId,
                s.store_name AS storeName,
                m.min_price AS minPrice,
                m.point_percent AS pointPercent,
                um.is_complete AS isComplete
            FROM member_mission um
            JOIN mission m ON um.mission_id = m.mission_id
            JOIN store s ON m.store_id = s.store_id
            WHERE um.member_id = :memberId
              AND um.is_complete = :isComplete
            ORDER BY um.is_complete ASC, um.user_mission_id ASC
            LIMIT :limit OFFSET :offset
            """, nativeQuery = true)
    List<MissionListCursorRow> findMissionListPage(
            @Param("memberId") Long memberId,
            @Param("isComplete") boolean isComplete,
            @Param("offset") int offset,
            @Param("limit") int limit
    );

    @Query(value = """
            SELECT COUNT(*)
            FROM member_mission um
            JOIN mission m ON um.mission_id = m.mission_id
            JOIN store s ON m.store_id = s.store_id
            WHERE um.member_id = :memberId
              AND um.is_complete = :isComplete
            """, nativeQuery = true)
    long countMissionList(@Param("memberId") Long memberId,
                          @Param("isComplete") boolean isComplete);

    @Query(value = """
            SELECT
                s.store_id AS storeId,
                s.store_name AS storeName,
                s.store_category AS storeCategory,
                m.d_day AS dDay,
                m.min_price AS minPrice,
                m.point AS point,
                um.user_mission_id AS userMissionId
            FROM member u
            JOIN member_mission um ON u.member_id = um.member_id
            JOIN mission m ON um.mission_id = m.mission_id
            JOIN store s ON m.store_id = s.store_id
            JOIN location l ON s.location_id = l.location_id
              WHERE u.member_id = :memberId
              AND l.name = :locationName
              AND um.is_complete = :isComplete
            ORDER BY m.d_day ASC, um.user_mission_id ASC
            LIMIT :limit OFFSET :offset
            """, nativeQuery = true)
    List<HomeMissionCursorRow> findHomeMissionsPage(
            @Param("memberId") Long memberId,
            @Param("locationName") String locationName,
            @Param("isComplete") boolean isComplete,
            @Param("offset") int offset,
            @Param("limit") int limit
    );

    @Query(value = """
            SELECT COUNT(*)
            FROM member u
            JOIN member_mission um ON u.member_id = um.member_id
            JOIN mission m ON um.mission_id = m.mission_id
            JOIN store s ON m.store_id = s.store_id
            JOIN location l ON s.location_id = l.location_id
              WHERE u.member_id = :memberId
              AND l.name = :locationName
              AND um.is_complete = :isComplete
            """, nativeQuery = true)
    long countHomeMissions(@Param("memberId") Long memberId,
                           @Param("locationName") String locationName,
                           @Param("isComplete") boolean isComplete);
}

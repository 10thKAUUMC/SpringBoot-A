package com.example.umc_10th.domain.mission.repository;

import com.example.umc_10th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    @Query(
            value = """
                select m
                from Mission m
                join fetch m.store s
                join s.region r
                where r.id = :regionId
                """,
            countQuery = """
                select count(m)
                from Mission m
                join m.store s
                join s.region r
                where r.id = :regionId
                """
    )
    Page<Mission> findHomeMissionsByRegionId(
            Long regionId,
            Pageable pageable
    );
}

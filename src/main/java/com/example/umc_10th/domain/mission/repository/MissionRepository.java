package com.example.umc_10th.domain.mission.repository;

import com.example.umc_10th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    Page<Mission> findAllByStore_Id(Long storeId, Pageable pageable);

    Page<Mission> findMissionsByStore_IdAndIdLessThanOrderByIdDesc(Long storeId, Long id, Pageable pageable);
    Page<Mission> findMissionsByStore_IdOrderByIdDesc(Long storeId, Pageable pageable);
}

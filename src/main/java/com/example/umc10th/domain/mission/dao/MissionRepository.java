package com.example.umc10th.domain.mission.dao;

import com.example.umc10th.domain.mission.domain.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long>{
    //If you wanna find specific mission from a store, you can add something like this :)
    //List<Mission> findAllByStoreId(Long storeId);
}

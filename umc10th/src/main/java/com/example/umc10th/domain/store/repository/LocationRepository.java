package com.example.umc10th.domain.store.repository;

import com.example.umc10th.domain.store.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Long> {

    Optional<Location> findByName(String name);
}

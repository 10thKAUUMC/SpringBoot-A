package com.example.umc10th.domain.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.umc10th.domain.store.domain.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {


}

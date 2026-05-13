package com.example.umc_10th.domain.users.repository;

import com.example.umc_10th.domain.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

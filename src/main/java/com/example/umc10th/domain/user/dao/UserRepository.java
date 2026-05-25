package com.example.umc10th.domain.user.dao;

import com.example.umc10th.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //This is dao which stands for Data Access Object
    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);

}

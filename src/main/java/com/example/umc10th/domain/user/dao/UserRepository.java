package com.example.umc10th.domain.user.dao;

import com.example.umc10th.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //This is dao which stands for Data Access Object

    //Also, this is just an interface, where Spring Data JPA do all the work.

    //Here we usually put/provide save(), findById(), findAll(), delete() etc.
}

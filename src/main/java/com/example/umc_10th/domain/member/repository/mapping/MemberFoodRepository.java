package com.example.umc_10th.domain.member.repository.mapping;

import com.example.umc_10th.domain.member.entity.mapping.MemberFood;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberFoodRepository extends JpaRepository<MemberFood, Long> {
    List<MemberFood> findByMemberId(Long memberId);
}

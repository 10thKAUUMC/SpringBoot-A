package com.example.umc_10th.domain.member.repository.mapping;

import com.example.umc_10th.domain.member.entity.mapping.MemberTerm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MemberTermRepository extends JpaRepository<MemberTerm, Long> {
    List<MemberTerm> findByMemberId(Long memberId);
}
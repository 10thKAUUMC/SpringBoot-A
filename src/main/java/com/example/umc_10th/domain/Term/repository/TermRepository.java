package com.example.umc_10th.domain.Term.repository;

import com.example.umc_10th.domain.Term.entity.Term;
import com.example.umc_10th.domain.Term.enums.RequirementType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TermRepository extends JpaRepository<Term, Long> {
    List<Term> findByRequired(RequirementType required);
}

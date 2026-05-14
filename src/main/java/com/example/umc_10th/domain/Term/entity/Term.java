package com.example.umc_10th.domain.Term.entity;

import com.example.umc_10th.domain.Term.enums.RequirementType;
import com.example.umc_10th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "term")
public class Term extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "required")
    @Enumerated(EnumType.STRING)
    private RequirementType required;

    @Column(name = "content")
    private String content;
}

package com.example.umc_10th.domain.mission.entity;

import com.example.umc_10th.domain.store.entity.Store;
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
@Table(name = "mission")
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "point", nullable = false)
    private Integer point;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;
}

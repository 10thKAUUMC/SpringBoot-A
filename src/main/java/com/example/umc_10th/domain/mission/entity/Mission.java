package com.example.umc_10th.domain.mission.entity;

import com.example.umc_10th.domain.store.entity.Store;
import com.example.umc_10th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "mission")
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mission_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @Column(name = "mission_title", length = 50)
    private String missionTitle;

    @Column(name = "mission_point")
    private Integer missionPoint;

    @Column(name = "mission_spec", length = 50)
    private String missionSpec;

    @Column(name = "deadline")
    private Integer deadline;
}

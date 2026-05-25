package com.example.umc10th.domain.mission.domain;

import com.example.umc10th.domain.store.domain.Store;
import com.example.umc10th.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor



public class Mission extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Integer reward; //Points when you complete a mission

    private LocalDate deadline; //Due date

    @Column(columnDefinition =  "TEXT")
    private String missionSpec; //Mission description

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

}

package com.example.umc_10th.domain.membermission.entity;

import com.example.umc_10th.domain.mission.entity.Mission;
import com.example.umc_10th.domain.mission.enums.MissionStatus;
import com.example.umc_10th.domain.users.entity.User;
import com.example.umc_10th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "membermission")
public class MemberMission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "membermission_id")
    private Long id;

    @Column(name = "clear_date")
    private LocalDateTime clearDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private MissionStatus state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;
}

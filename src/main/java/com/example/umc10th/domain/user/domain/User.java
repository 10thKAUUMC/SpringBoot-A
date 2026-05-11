package com.example.umc10th.domain.user.domain;

import com.example.umc10th.domain.mission.domain.UserMission;
import com.example.umc10th.domain.review.domain.Review;
import com.example.umc10th.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity //Declaring matching entity with the DB
@Getter //Automatically creates method when it's brought
@Builder //Builds class safely and conveniently
@NoArgsConstructor(access = AccessLevel.PROTECTED) //Builder that has no parameter (Essential JPA)
@AllArgsConstructor //Builder that as every field (pairs with builder)

public class User extends BaseEntity { //We need to know the time info, so inherit BaseEntity

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DB automatically creates ID (MySQL's Auto Increment)
    private long id;

    @Column(nullable = false, unique = true, length = 50)
    private String nickname;

    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @Column(length = 20)
    private String phoneNumber;

    private Integer points;

    //Mapping is_verified from ERD (default = false)
    @Column(columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean isVerified;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserMission> userMissionList = new ArrayList<>();

}

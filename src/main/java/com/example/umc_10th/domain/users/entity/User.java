package com.example.umc_10th.domain.users.entity;

import com.example.umc_10th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "users")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "login_id", length = 20)
    private String loginId;

    @Column(name = "password", length = 100)
    private String password;

    @Column(name = "user_name", length = 20)
    private String userName;

    @Column(name = "email", length = 20)
    private String email;

    @Column(name = "total_point")
    private Integer totalPoint;

    @Column(name = "phone_number", length = 13)
    private String phoneNumber;

    @Column(name = "is_authenticated")
    private Boolean isAuthenticated;
}

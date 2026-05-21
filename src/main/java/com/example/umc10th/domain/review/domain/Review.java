package com.example.umc10th.domain.review.domain;

import com.example.umc10th.domain.store.domain.Store;
import com.example.umc10th.domain.user.domain.User;
import com.example.umc10th.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class Review extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private Float score;

    @Column(columnDefinition = "TEXT") //Content might be long so it'll be TEXT type.
    private String content;


    @ManyToOne(fetch = FetchType.LAZY) // User can write multiple reviews
    //...Lazy: Data should be slow and steady, or else... it will crash out;;
    @JoinColumn(name = "user_id") // From DB, this is FK name
    private User user;

    @ManyToOne(fetch = FetchType.LAZY) //There can be multiple reviews on a single store
    @JoinColumn(name = "store_id") // From DB, this is FK name
    private Store store;

}

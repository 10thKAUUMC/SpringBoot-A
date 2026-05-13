package com.example.umc_10th.domain.store.entity;

import com.example.umc_10th.domain.region.entity.Region;
import com.example.umc_10th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "store")
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long id;

    @Column(name = "store_name", length = 20)
    private String storeName;

    @Column(name = "category", length = 20)
    private String category;

    @Column(name = "address", length = 100)
    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;
}

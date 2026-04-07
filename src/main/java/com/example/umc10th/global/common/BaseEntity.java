package com.example.umc10th.global.common;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


@MappedSuperclass //Shows mapping info to only inherited Entities
@EntityListeners(AuditingEntityListener.class) //Detecting the change in Entity (Create, Edit)
@Getter
public abstract class BaseEntity {
    //Abstract for safety measure where it cannot do anything by itself, only used when it's inherited

    @CreatedDate //Automatic save the date when entity created
    @Column(updatable = false) //Created date cannot be editable
    private LocalDateTime createdAt;

    @LastModifiedBy //Automatic save when entity change its value
    private LocalDateTime updatedAt;

}

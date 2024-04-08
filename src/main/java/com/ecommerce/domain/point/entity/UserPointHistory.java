package com.ecommerce.domain.point.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.ZonedDateTime;

@Getter
@NoArgsConstructor
@Table(name="UserPointHistory")
@Entity
public class UserPointHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    Long id = 0L;

    @Column(name = "user_id", nullable = false)
    Long userId;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    PointStatus type = PointStatus.CHARGE;

    @Column(name="point", nullable = false)
    Long point;

    @CreationTimestamp
    @Column(name="created_at", nullable = false, insertable = true, updatable = false)
    ZonedDateTime createdAt;

    public UserPointHistory(Long userId, PointStatus type, Long point){
        this.userId = userId;
        this.type = type;
        this.point = point;
    }
}

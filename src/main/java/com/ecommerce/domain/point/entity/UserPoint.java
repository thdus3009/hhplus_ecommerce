package com.ecommerce.domain.point.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@Table(name="UserPoint")
@Entity
public class UserPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    Long id = 0L;

    @Column(name = "user_id", nullable = false)
    Long userId;

    @Column(name="point", nullable = false)
    Long point;

    @CreationTimestamp
    @Column(name="created_at", nullable = false, insertable = true, updatable = false)
    ZonedDateTime createdAt;

    @UpdateTimestamp
    @Column(name="updated_at", nullable = true, insertable = false, updatable = true)
    ZonedDateTime updatedAt;

    public UserPoint(Long userId, Long point){
        this.userId = userId;
        this.point = point;
    }
}

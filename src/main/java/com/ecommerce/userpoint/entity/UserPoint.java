package com.ecommerce.userpoint.entity;

import java.time.ZonedDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Table(name = "UserPoint")
@Entity
public class UserPoint {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	Long id = 0L;

	@Column(name = "user_id", nullable = false)
	Long userId;

	@Column(name = "point", nullable = false)
	Long point;

	@CreationTimestamp
	@Column(name = "created_at", nullable = false, insertable = true, updatable = false)
	ZonedDateTime createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at", nullable = true, insertable = false, updatable = true)
	ZonedDateTime updatedAt;

	public UserPoint(Long userId, Long point) {
		this.userId = userId;
		this.point = point;
	}

	public UserPoint(Long id, Long userId, Long point, ZonedDateTime createdAt, ZonedDateTime updatedAt) {
		this.id = id;
		this.userId = userId;
		this.point = point;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public void discount(Long usePoint) {
		this.point = this.point - usePoint;
	}

	public void add(Long chargePoint) {
		this.point = this.point + chargePoint;
	}

	public void isEnoughPointToPay(Long usePoint) {
		if (this.point < usePoint) {
			throw new IllegalArgumentException((usePoint - this.point) + "만큼의 잔액이 부족합니다.");
		}
	}
}

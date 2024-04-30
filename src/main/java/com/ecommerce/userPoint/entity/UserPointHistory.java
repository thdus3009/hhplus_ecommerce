package com.ecommerce.userPoint.entity;

import java.time.ZonedDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Table(name = "UserPointHistory")
@Entity
public class UserPointHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id = 0L;

	@Column(name = "user_id", nullable = false)
	private Long userId;

	@Column(name = "type", nullable = false)
	@Enumerated(EnumType.STRING)
	private PointStatus type = PointStatus.CHARGE;

	@Column(name = "point", nullable = false)
	private Long point;

	@CreationTimestamp
	@Column(name = "created_at", nullable = false, insertable = true, updatable = false)
	private ZonedDateTime createdAt;

	public UserPointHistory(Long userId, PointStatus type, Long point) {
		this.userId = userId;
		this.type = type;
		this.point = point;
	}
}

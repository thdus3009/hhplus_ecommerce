package com.ecommerce.item.entity;

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
@Entity
@Table(name = "Item")
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	Long id = 0L;

	@Column(name = "name", nullable = false)
	String name;

	@Column(name = "price", nullable = false)
	Long price;

	@Column(name = "quantity", nullable = false)
	Long quantity;

	@CreationTimestamp
	@Column(name = "created_at", nullable = false, insertable = true, updatable = false)
	ZonedDateTime createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at", nullable = true, insertable = false, updatable = true)
	ZonedDateTime updatedAt = null;

	public Item(Long id, String name, Long price, Long quantity, ZonedDateTime createdAt) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.createdAt = createdAt;
	}

	public void updateQuantity(Long quantity) {
		this.quantity = quantity;
	}
}

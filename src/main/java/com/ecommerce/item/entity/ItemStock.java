package com.ecommerce.item.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "ItemStock")
public class ItemStock {
	@Id
	@Column(name = "item_id", nullable = false, updatable = false)
	Long itemId;

	@Column(name = "quantity", nullable = false)
	Long quantity;

	public ItemStock(Long itemId, Long quantity) {
		this.itemId = itemId;
		this.quantity = quantity;
	}

	public void decreaseStock(Long quantity) {
		this.quantity = this.quantity - quantity;
	}

	public void updateQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public void checkEnoughItemStockQuantity(Long quantity) {
		if (this.quantity < quantity) {
			throw new IllegalArgumentException("[" + this.itemId + "] 상품 재고가 부족합니다.");
		}
	}
}

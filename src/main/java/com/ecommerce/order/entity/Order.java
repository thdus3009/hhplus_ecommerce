package com.ecommerce.order.entity;

import java.time.ZonedDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "Orders", indexes = @Index(
	name = "idx_order_at",
	columnList = "order_date"))
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id = 0L;

	// uuid(유효아이디)
	@Column(name = "uuid", nullable = false, unique = true)
	private String uuid;

	@Column(name = "user_id", nullable = false)
	private Long userId;

	@Column(name = "total_price", nullable = false)
	private Long totalPrice;

	@Column(name = "total_count", nullable = false)
	private Long totalCount;

	@Column(name = "receiver_name", nullable = false)
	private String receiverName;

	@Column(name = "receiver_phone", nullable = false)
	private String receiverPhone;

	@Column(name = "receiver_address", nullable = false)
	private String receiverAddress;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OrderItem> orderItems;

	@CreationTimestamp
	@Column(name = "order_date", nullable = false, insertable = true, updatable = false)
	private ZonedDateTime orderDate = ZonedDateTime.now();

	public Order(String uuid, Long userId, Long totalPrice, Long totalCount, String receiverName, String receiverPhone,
		String receiverAddress) {
		this.uuid = uuid;
		this.userId = userId;
		this.totalPrice = totalPrice;
		this.totalCount = totalCount;
		this.receiverName = receiverName;
		this.receiverPhone = receiverPhone;
		this.receiverAddress = receiverAddress;
	}

}

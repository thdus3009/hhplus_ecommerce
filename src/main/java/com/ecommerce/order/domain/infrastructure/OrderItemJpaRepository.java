package com.ecommerce.order.domain.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.order.entity.OrderItem;

@Repository
public interface OrderItemJpaRepository extends JpaRepository<OrderItem, Long> {
}

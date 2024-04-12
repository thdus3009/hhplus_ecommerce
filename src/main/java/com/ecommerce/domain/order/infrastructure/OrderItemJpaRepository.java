package com.ecommerce.domain.order.infrastructure;

import com.ecommerce.domain.order.entity.Order;
import com.ecommerce.domain.order.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemJpaRepository extends JpaRepository<OrderItem, Long> {
}

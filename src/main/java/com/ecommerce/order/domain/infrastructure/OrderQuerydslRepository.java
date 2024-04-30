package com.ecommerce.order.domain.infrastructure;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Repository
public class OrderQuerydslRepository {
	private final JPAQueryFactory queryFactory;

	public void findOrderId() {

	}
}

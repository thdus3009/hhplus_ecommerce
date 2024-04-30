package com.ecommerce.item.domain.infrastructure;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class ItemStockQuerydslRepository {
	JPAQueryFactory queryFactory;

}

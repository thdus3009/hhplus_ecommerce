package com.ecommerce.order.domain.infrastructure;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@AllArgsConstructor
@Repository
public class OrderQuerydslRepository {
    private final JPAQueryFactory queryFactory;

    public void findOrderId(){

    }
}
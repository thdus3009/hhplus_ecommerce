package com.ecommerce.domain.item.infrastructure;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

@Repository
public class ItemStockQuerydslRepository {
    JPAQueryFactory queryFactory;


}

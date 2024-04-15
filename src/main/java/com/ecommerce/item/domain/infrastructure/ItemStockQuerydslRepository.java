package com.ecommerce.item.domain.infrastructure;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

@Repository
public class ItemStockQuerydslRepository {
    JPAQueryFactory queryFactory;


}

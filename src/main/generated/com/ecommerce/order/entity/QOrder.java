package com.ecommerce.order.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrder is a Querydsl query type for Order
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrder extends EntityPathBase<Order> {

    private static final long serialVersionUID = 1993834430L;

    public static final QOrder order = new QOrder("order1");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.ZonedDateTime> orderDate = createDateTime("orderDate", java.time.ZonedDateTime.class);

    public final ListPath<OrderItem, QOrderItem> orderItems = this.<OrderItem, QOrderItem>createList("orderItems", OrderItem.class, QOrderItem.class, PathInits.DIRECT2);

    public final StringPath receiverAddress = createString("receiverAddress");

    public final StringPath receiverName = createString("receiverName");

    public final StringPath receiverPhone = createString("receiverPhone");

    public final NumberPath<Long> totalCount = createNumber("totalCount", Long.class);

    public final NumberPath<Long> totalPrice = createNumber("totalPrice", Long.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public final StringPath uuid = createString("uuid");

    public QOrder(String variable) {
        super(Order.class, forVariable(variable));
    }

    public QOrder(Path<? extends Order> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOrder(PathMetadata metadata) {
        super(Order.class, metadata);
    }

}


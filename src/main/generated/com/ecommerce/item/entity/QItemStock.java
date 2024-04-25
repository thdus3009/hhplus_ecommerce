package com.ecommerce.item.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QItemStock is a Querydsl query type for ItemStock
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QItemStock extends EntityPathBase<ItemStock> {

    private static final long serialVersionUID = 608825144L;

    public static final QItemStock itemStock = new QItemStock("itemStock");

    public final NumberPath<Long> itemId = createNumber("itemId", Long.class);

    public final NumberPath<Long> quantity = createNumber("quantity", Long.class);

    public QItemStock(String variable) {
        super(ItemStock.class, forVariable(variable));
    }

    public QItemStock(Path<? extends ItemStock> path) {
        super(path.getType(), path.getMetadata());
    }

    public QItemStock(PathMetadata metadata) {
        super(ItemStock.class, metadata);
    }

}


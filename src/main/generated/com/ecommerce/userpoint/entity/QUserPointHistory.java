package com.ecommerce.userpoint.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserPointHistory is a Querydsl query type for UserPointHistory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserPointHistory extends EntityPathBase<UserPointHistory> {

    private static final long serialVersionUID = -437586026L;

    public static final QUserPointHistory userPointHistory = new QUserPointHistory("userPointHistory");

    public final DateTimePath<java.time.ZonedDateTime> createdAt = createDateTime("createdAt", java.time.ZonedDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> point = createNumber("point", Long.class);

    public final EnumPath<PointStatus> type = createEnum("type", PointStatus.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QUserPointHistory(String variable) {
        super(UserPointHistory.class, forVariable(variable));
    }

    public QUserPointHistory(Path<? extends UserPointHistory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserPointHistory(PathMetadata metadata) {
        super(UserPointHistory.class, metadata);
    }

}


package com.example.jpademo.domain.locker;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDuck is a Querydsl query type for Duck
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDuck extends EntityPathBase<Duck> {

    private static final long serialVersionUID = 699288070L;

    public static final QDuck duck = new QDuck("duck");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QDuck(String variable) {
        super(Duck.class, forVariable(variable));
    }

    public QDuck(Path<? extends Duck> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDuck(PathMetadata metadata) {
        super(Duck.class, metadata);
    }

}


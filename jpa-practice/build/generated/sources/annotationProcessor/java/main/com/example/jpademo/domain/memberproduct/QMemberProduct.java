package com.example.jpademo.domain.memberproduct;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberProduct is a Querydsl query type for MemberProduct
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberProduct extends EntityPathBase<MemberProduct> {

    private static final long serialVersionUID = -1024436781L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberProduct memberProduct = new QMemberProduct("memberProduct");

    public final QMemberProductId2 memberProductId2;

    public final NumberPath<Integer> orderAmount = createNumber("orderAmount", Integer.class);

    public QMemberProduct(String variable) {
        this(MemberProduct.class, forVariable(variable), INITS);
    }

    public QMemberProduct(Path<? extends MemberProduct> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberProduct(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberProduct(PathMetadata metadata, PathInits inits) {
        this(MemberProduct.class, metadata, inits);
    }

    public QMemberProduct(Class<? extends MemberProduct> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.memberProductId2 = inits.isInitialized("memberProductId2") ? new QMemberProductId2(forProperty("memberProductId2")) : null;
    }

}


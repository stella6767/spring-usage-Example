package com.example.jpademo.domain.memberproduct;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMemberProductId2 is a Querydsl query type for MemberProductId2
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QMemberProductId2 extends BeanPath<MemberProductId2> {

    private static final long serialVersionUID = 1041535908L;

    public static final QMemberProductId2 memberProductId2 = new QMemberProductId2("memberProductId2");

    public final StringPath member = createString("member");

    public final StringPath product = createString("product");

    public QMemberProductId2(String variable) {
        super(MemberProductId2.class, forVariable(variable));
    }

    public QMemberProductId2(Path<? extends MemberProductId2> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMemberProductId2(PathMetadata metadata) {
        super(MemberProductId2.class, metadata);
    }

}


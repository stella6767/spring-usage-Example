package com.example.jpademo.domain.member;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = 1638862565L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMember member = new QMember("member1");

    public final QAddress address;

    public final NumberPath<Integer> age = createNumber("age", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.example.jpademo.domain.locker.QLocker locker;

    public final ListPath<com.example.jpademo.domain.order.Order, com.example.jpademo.domain.order.QOrder> orders = this.<com.example.jpademo.domain.order.Order, com.example.jpademo.domain.order.QOrder>createList("orders", com.example.jpademo.domain.order.Order.class, com.example.jpademo.domain.order.QOrder.class, PathInits.DIRECT2);

    public final com.example.jpademo.domain.team.QTeam team;

    public final StringPath username = createString("username");

    public final BooleanPath vip = createBoolean("vip");

    public QMember(String variable) {
        this(Member.class, forVariable(variable), INITS);
    }

    public QMember(Path<? extends Member> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMember(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMember(PathMetadata metadata, PathInits inits) {
        this(Member.class, metadata, inits);
    }

    public QMember(Class<? extends Member> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.address = inits.isInitialized("address") ? new QAddress(forProperty("address")) : null;
        this.locker = inits.isInitialized("locker") ? new com.example.jpademo.domain.locker.QLocker(forProperty("locker"), inits.get("locker")) : null;
        this.team = inits.isInitialized("team") ? new com.example.jpademo.domain.team.QTeam(forProperty("team")) : null;
    }

}


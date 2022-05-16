package com.example.jpademo;

import com.example.jpademo.domain.member.Member;
import com.example.jpademo.domain.member.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
class JpademoApplicationTests {



    @Autowired
    EntityManager em;

    @Test
    void contextLoads() {

        Member member = new Member("홍길동");
        em.persist(member);

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        //QMember qMember = new QMember("m");
        QMember qMember = QMember.member;

        Member result = queryFactory
                .selectFrom(qMember)
                .fetchOne();

        Assertions.assertThat(result).isEqualTo(member);
        Assertions.assertThat(result.getId()).isEqualTo(member.getId());

    }




}

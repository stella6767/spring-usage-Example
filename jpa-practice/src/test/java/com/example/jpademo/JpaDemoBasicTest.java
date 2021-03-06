package com.example.jpademo;


import com.example.jpademo.domain.member.Member;
import com.example.jpademo.domain.member.QMember;
import com.example.jpademo.domain.team.QTeam;
import com.example.jpademo.domain.team.Team;
import com.example.jpademo.dto.MemberDto;
import com.example.jpademo.dto.MyMemberDto;
import com.example.jpademo.dto.QMemberDto;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;

import static com.example.jpademo.domain.member.QMember.*;
import static com.example.jpademo.domain.team.QTeam.*;
import static com.querydsl.jpa.JPAExpressions.*;
import static org.assertj.core.api.Assertions.*;


@SpringBootTest
@Transactional
public class JpaDemoBasicTest {

    private static final Logger log = LoggerFactory.getLogger(JpaDemoBasicTest.class);

    @Autowired
    EntityManager em;

    JPAQueryFactory queryFactory;

    @BeforeEach
    public void beforeEach() {
        queryFactory = new JPAQueryFactory(em);

        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);
        Member member3 = new Member("member3", 30, teamB);
        Member member4 = new Member("member4", 40, teamB);

        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);

        //?????????
        em.flush();
        em.clear();

        List<Member> members = em.createQuery("select m from Member m", Member.class)
                .getResultList();

        for (Member member : members){
            log.info("member =>{}", member);
            log.info("member.team =>{}", member.getTeam());
        }
    }


    @Test
    public void startJPQL(){
        //member1 ??? ?????????
        Member findMember = em.createQuery("select m from Member m where m.username = :username", Member.class)
                .setParameter("username", "member1")
                .getSingleResult();

        assertThat(findMember.getUsername()).isEqualTo("member1");
    }


    @Test
    public void startQuerydsl(){
        //JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        //QMember m = new QMember("m"); //?????? ???????????? ???????????? ??? ?????? ????????? ????????? ????????????..

        Member findMember = queryFactory
                .select(member)
                .from(member)
                .where(member.username.eq("member1"))
                .fetchOne();
        assertThat(findMember.getUsername()).isEqualTo("member1");

    }

    @Test
    public void searchTest(){

        //cmd + option + v
        Member member = queryFactory
                .selectFrom(QMember.member)
                .where(QMember.member.username.eq("member1")
                        .and(QMember.member.age.between(10,30)))
                .fetchOne();

        assertThat(member.getUsername()).isEqualTo("member1");
    }

    @Test
    public void searchAndParam(){

        //cmd + option + v
        Member member = queryFactory
                .selectFrom(QMember.member)
                .where(
                        QMember.member.username.eq("member1"),
                        (QMember.member.age.between(10,30)) //?????? searchTest??? ???????????? null??? ????????????.
                )
                .fetchOne();

        assertThat(member.getUsername()).isEqualTo("member1");
    }

    @Test
    public void resultFetch(){

//        List<Member> fetch = queryFactory
//                .selectFrom(member)
//                .fetch();
//        Member fetchOne = queryFactory
//                .selectFrom(member)
//                .fetchOne();
//
//        Member fetchFirst = queryFactory
//                .selectFrom(member)
//                .fetchFirst();
//
//        QueryResults<Member> results = queryFactory
//                .selectFrom(member)
//                .fetchResults();
//        List<Member> content = results.getResults();
//        results.getTotal();
//
//        long total = queryFactory
//                .selectFrom(member)
//                .fetchCount();

    }


    /**
     * ?????? ?????? ??????
     * 1. ?????? ?????? ????????????(desc)
     * 2. ?????? ?????? ????????????(asc)
     * ??? 2?????? ?????? ????????? ????????? ???????????? ??????(null last)
     */

    @Test
    public void sortTest(){

        em.persist(new Member(null, 100));
        em.persist(new Member("member5", 100));
        em.persist(new Member("member6", 100));

        List<Member> result = queryFactory
                .selectFrom(member)
                .where(member.age.eq(100))
                .orderBy(member.age.desc(), member.username.asc().nullsLast())
                .fetch();

        Member member5 = result.get(0);
        Member member6 = result.get(1);
        Member memberNull = result.get(2);

        assertThat(member5.getUsername()).isEqualTo("member5");
        assertThat(member6.getUsername()).isEqualTo("member6");
        assertThat(memberNull.getUsername()).isNull();

    }

    @Test
    public void pagingTest(){

        List<Member> fetch = queryFactory
                .selectFrom(member)
                .orderBy(member.username.desc())
                .offset(1)
                .limit(2)
                .fetch();

        assertThat(fetch.size()).isEqualTo(2);
    }

    @Test
    public void aggregation(){
        List<Tuple> result = queryFactory
                .select(
                        member.count(),
                        member.age.sum(),
                        member.age.avg(),
                        member.age.max(),
                        member.age.min()
                )
                .from(member)
                .fetch();

        Tuple tuple = result.get(0);
        assertThat(tuple.get(member.count())).isEqualTo(4);

    }

    //tdd ????????? ?????????
    /**
     * ?????? ????????? ??? ?????? ?????? ????????? ?????????
     *
     */
    @Test
    public void group(){

        List<Tuple> result = queryFactory
                .select(team.name, member.age.avg())
                .from(member)
                .join(member.team, team)
                .groupBy(team.name)
                //.having()
                .fetch();

        Tuple teamA = result.get(0);
        Tuple teamB = result.get(1);

        assertThat(teamA.get(team.name)).isEqualTo("teamA");

    }

    @Test
    public void join(){

        List<Member> result = queryFactory
                .selectFrom(member)
                .leftJoin(member.team, team) //?????? join??? innnerjoin
                .where(team.name.eq("teamA"))
                .fetch();

        assertThat(result).extracting("username")
                .containsExactly("member1" , "member2");
    }

    /**
     * ?????? ??????
     * ????????? ????????? ??? ????????? ?????? ?????? ??????
     */

    @Test
    public void thetaJoin(){

        //???????????? ????????? ??????
        em.persist(new Member("teamA"));
        em.persist(new Member("teamB"));


        List<Member> result = queryFactory
                .select(member)
                .from(member, team) //from ?????? ?????? ???????????? ???????????? ?????? ??????, ?????? outer join??? ?????????????????????..?????? on ???????????? ??????
                .where(member.username.eq(team.name))
                .fetch();

        assertThat(result).extracting("username").containsExactly("teamA", "teamB");

    }


    /**
     * ????????? ?????? ???????????????, ??? ????????? teamA??? ?????? ??????, ????????? ?????? ??????
     */

    @Test
    public void joinOnFiltering(){

        List<Tuple> result = queryFactory
                .select(member, team)
                .from(member)
                .leftJoin(member.team, team).on(team.name.eq("teamA"))
                .fetch();

        for (Tuple tuple : result) {  //iter ??????
            System.out.println("tuple = " + tuple);

        }

    }


    /**
     * ???????????? ?????? ????????? ?????? ??????
     * ????????? ????????? ??? ????????? ?????? ?????? ?????? ??????
     */

    @Test
    public void joinOnNoRelation(){

        //???????????? ????????? ??????
        em.persist(new Member("teamA"));
        em.persist(new Member("teamB"));
        em.persist(new Member("teamC"));


        List<Tuple> result = queryFactory
                .select(member, team)
                .from(member)
                .leftJoin(team).on(member.username.eq(team.name))
                .fetch();

        for (Tuple tuple : result) {
            System.out.println("tuple " + tuple);
        }
    }

    //


    @PersistenceUnit
    EntityManagerFactory emf;

    @Test
    public void fetchJoinNo(){
        em.flush();
        em.clear();

        Member findMember = queryFactory
                .selectFrom(QMember.member)
                .where(QMember.member.username.eq("member1"))
                .fetchOne();

        boolean loaded = emf.getPersistenceUnitUtil().isLoaded(findMember.getTeam());
        assertThat(loaded).as("?????? ?????? ?????????").isFalse();

    }


    /**
     * ?????? ????????? ?????????
     */

    @Test
    public void fetchJoinUse(){
        em.flush();
        em.clear();

        Member findMember = queryFactory
                .selectFrom(member)
                .join(member.team, team).fetchJoin()
                .where(member.username.eq("member1"))
                .fetchOne();

        boolean loaded = emf.getPersistenceUnitUtil().isLoaded(findMember.getTeam());
        assertThat(loaded).as("?????? ?????? ?????????").isTrue();

    }


    /**
     * ????????? ?????? ?????? ?????? ??????
     */

    @Test
    public void subQuery(){

        QMember memberSub = new QMember("memberSub");

        List<Member> result = queryFactory
                .selectFrom(member)
                .where(member.age.eq(
                        select(memberSub.age.max())
                                .from(memberSub)
                ))
                .fetch();

    }



    /**
     * ????????? ?????? ?????? ??????
     */

    @Test
    public void subQueryGoe(){

        QMember memberSub = new QMember("memberSub");

        List<Member> result = queryFactory
                .selectFrom(member)
                .where(member.age.goe(
                        select(memberSub.age.avg())
                                .from(memberSub)
                ))
                .fetch();

        assertThat(result).extracting("age").containsExactly(30, 40);
    }



    @Test
    public void subQueryIn(){

        QMember memberSub = new QMember("memberSub");

        List<Member> result = queryFactory
                .selectFrom(member)
                .where(member.age.in(
                        select(memberSub.age)
                                .from(memberSub)
                                .where(memberSub.age.gt(10))
                ))
                .fetch();

        assertThat(result).extracting("age").containsExactly(20, 30, 40);
    }



    @Test
    public void selectSubQuery(){
        QMember memberSub = new QMember("memberSub");


        List<Tuple> result = queryFactory
                .select(member.username,
                        select(memberSub.age.avg())  //JPAExpressions static import
                                .from(memberSub)
                )
                .from(member)
                .fetch();


        for (Tuple tuple : result) {
            System.out.println("tuple" + tuple);

        }
    }


    /**
     * from ?????? ???????????? ??????.. join?????? ???????????????.. ????????? 2??? ???????????? ???????????????.. nativeSql??? ????????????.
     * ?????? ??? ??? ????????? ???????????? ??????.. ?????? ??? ??? ????????????, ???????????? ?????? ??? ??????.
     * sql anti pattern ???
     */


    @Test
    public void basicCase(){
        List<String> result = queryFactory
                .select(member.age
                        .when(10).then("??????")
                        .when(20).then("?????? ???")
                        .otherwise("??????"))
                .from(member)
                .fetch();

        for (String s : result) {
            System.out.println("S => " + s);
        }

    }


    @Test
    public void complexCase(){

        List<String> result = queryFactory
                .select(new CaseBuilder()
                        .when(member.age.between(0, 20)).then("0~20???")
                        .when(member.age.between(21, 30)).then("21~30???")
                        .otherwise("??????")
                )
                .from(member)
                .fetch();

        for (String s : result) {
            System.out.println("S => " + s);
        }
    }


    @Test
    public void constant(){


        List<Tuple> result = queryFactory
                .select(member.username, Expressions.constant("A"))
                .from(member)
                .fetch();

        for (Tuple tuple : result) {
            System.out.println("tuple=> " + tuple);

        }
    }




    @Test
    public void concat(){

        List<String> result = queryFactory
                .select(member.username.concat("_").concat(member.age.stringValue()))
                .from(member)
                .where(member.username.eq("member1"))
                .fetch();

        for (String s : result) {
            System.out.println("s => " + s);

        }
    }


    @Test
    public void simpleProjection(){

        List<String> result = queryFactory
                .select(member.username) //select ?????? ???????????? ?????? ????????????
                .from(member)
                .fetch();
    }


    @Test
    public void tupleProjection(){

        List<Tuple> result = queryFactory
                .select(member.username, member.age)
                .from(member)
                .fetch();  //tuple??? querydsl??? ??????????????????, ?????? ????????????????????? ?????? ?????? ???????????? ??? ??? ?????? dto ??? ????????????..

        for (Tuple tuple : result) {
            String username = tuple.get(member.username);

            System.out.println("username=>" + username);
        }
    }


    @Test
    public void findDtoByJPQL(){
        List<MemberDto> resultList = em.createQuery("select new com.example.jpademo.dto.MemberDto(m.username, m.age) from Member m", MemberDto.class)
                .getResultList();

        for (MemberDto memberDto : resultList) {
            System.out.println("memberDto => " + memberDto);

        }
    }

    /**
     * querydsl ????????? dto??? ???????????? 3?????? ??????
     * 1. ???????????? ??????
     * 2. ?????? ?????? ??????
     * 3. ????????? ??????
     */


    @Test
    public void findDtoBySetter(){

        List<MemberDto> result = queryFactory
                .select(Projections.bean(MemberDto.class,
                        member.username,
                        member.age
                ))
                .from(member)
                .fetch();

        System.out.println("result " + result);
    }



    @Test
    public void findDtoByField(){ //setter ????????? ?????? X

        List<MemberDto> result = queryFactory
                .select(Projections.fields(MemberDto.class,
                        member.username,
                        member.age
                ))
                .from(member)
                .fetch();

        System.out.println("result " + result);
    }


    @Test
    public void findDtoByConstructor(){ //setter ????????? ?????? X

        List<MemberDto> result = queryFactory
                .select(Projections.constructor(MemberDto.class,
                        member.username,
                        member.age
                ))
                .from(member)
                .fetch();

        System.out.println("result " + result);
    }


    @Test
    public void findMyDtoByField(){ //setter ????????? ?????? X

        List<MyMemberDto> result = queryFactory
                .select(Projections.fields(MyMemberDto.class,
                        member.username.as("name"), //????????? ?????? ???
                        member.age
                ))
                .from(member)
                .fetch();

        System.out.println("result " + result);
    }


    @Test
    public void findMyDto(){

        QMember memberSub = new QMember("memberSub");

        List<MyMemberDto> result = queryFactory
                .select(Projections.fields(MyMemberDto.class,
                        member.username.as("name"), //????????? ?????? ???

                        //??????????????? ?????? ??????
                        ExpressionUtils.as(JPAExpressions
                        .select(memberSub.age.max())
                        .from(memberSub), "age")
                ))
                .from(member)
                .fetch();

        System.out.println("result " + result);
    }


    @Test
    public void findMyDtoByConstructor(){ //setter ????????? ?????? X

        List<MyMemberDto> result = queryFactory
                .select(Projections.constructor(MyMemberDto.class,
                        member.username,
                        member.age
                ))
                .from(member)
                .fetch();

        System.out.println("result " + result);
    }



    @Test
    public void findDtoByQueryProjection(){
        //construtor ?????? ???????????? ????????? ????????????, ?????? ??????????????????

        List<MemberDto> result = queryFactory
                .select(new QMemberDto(member.username, member.age)) //cmd + p
                .from(member)
                .fetch();

        System.out.println("result " + result);
        //????????? dto??? querydsl??? ??????????????? ???.
    }





}

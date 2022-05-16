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

        //초기화
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
        //member1 을 찾아라
        Member findMember = em.createQuery("select m from Member m where m.username = :username", Member.class)
                .setParameter("username", "member1")
                .getSingleResult();

        assertThat(findMember.getUsername()).isEqualTo("member1");
    }


    @Test
    public void startQuerydsl(){
        //JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        //QMember m = new QMember("m"); //같은 테이블의 조인해야 될 경우 이렇게 이름을 달리해서..

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
                        (QMember.member.age.between(10,30)) //위의 searchTest와 동일하나 null을 무시한다.
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
     * 회원 정렬 순서
     * 1. 회원 나이 내림차순(desc)
     * 2. 회원 이름 오름차순(asc)
     * 단 2에서 회원 이름이 없으면 마지막에 출력(null last)
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

    //tdd 라이브 템플릿
    /**
     * 팀의 이름과 각 팀의 평균 연령을 구해라
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
                .leftJoin(member.team, team) //그냥 join은 innnerjoin
                .where(team.name.eq("teamA"))
                .fetch();

        assertThat(result).extracting("username")
                .containsExactly("member1" , "member2");
    }

    /**
     * 세타 조인
     * 회원의 이름이 팀 이름과 같은 회원 조회
     */

    @Test
    public void thetaJoin(){

        //연관관계 없어도 조인
        em.persist(new Member("teamA"));
        em.persist(new Member("teamB"));


        List<Member> result = queryFactory
                .select(member)
                .from(member, team) //from 절에 여러 엔티티를 선택해서 세타 조인, 대신 outer join은 불가능했었지만..조인 on 사용하면 가능
                .where(member.username.eq(team.name))
                .fetch();

        assertThat(result).extracting("username").containsExactly("teamA", "teamB");

    }


    /**
     * 회원과 팀을 조인하면서, 팀 이름이 teamA인 팀만 조인, 회원은 모두 조회
     */

    @Test
    public void joinOnFiltering(){

        List<Tuple> result = queryFactory
                .select(member, team)
                .from(member)
                .leftJoin(member.team, team).on(team.name.eq("teamA"))
                .fetch();

        for (Tuple tuple : result) {  //iter 단축
            System.out.println("tuple = " + tuple);

        }

    }


    /**
     * 연관관계 없는 엔티티 외부 조인
     * 회원의 이름이 팀 이름과 같은 대상 외부 조인
     */

    @Test
    public void joinOnNoRelation(){

        //연관관계 없어도 조인
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
        assertThat(loaded).as("패치 조인 미적용").isFalse();

    }


    /**
     * 아직 이해를 못했음
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
        assertThat(loaded).as("패치 조인 미적용").isTrue();

    }


    /**
     * 나이가 가장 많은 회원 조회
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
     * 나이가 평균 이상 회원
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
     * from 절의 서브쿼리 한계.. join으로 대체하거나.. 쿼리를 2번 분리해서 실행하거나.. nativeSql을 사용한다.
     * 굳이 한 방 쿼리에 집착하지 말자.. 쿼리 두 번 날린다고, 성능저하 굳이 안 된다.
     * sql anti pattern 책
     */


    @Test
    public void basicCase(){
        List<String> result = queryFactory
                .select(member.age
                        .when(10).then("열살")
                        .when(20).then("스무 살")
                        .otherwise("기타"))
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
                        .when(member.age.between(0, 20)).then("0~20살")
                        .when(member.age.between(21, 30)).then("21~30살")
                        .otherwise("기타")
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
                .select(member.username) //select 절에 나열하는 것을 프로젝션
                .from(member)
                .fetch();
    }


    @Test
    public void tupleProjection(){

        List<Tuple> result = queryFactory
                .select(member.username, member.age)
                .from(member)
                .fetch();  //tuple은 querydsl에 종속적이므로, 이걸 리턴하기보다는 모든 자바 생태계에 쓸 수 있는 dto 로 던져주자..

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
     * querydsl 결과를 dto로 반환하는 3가지 방법
     * 1. 프로퍼티 접근
     * 2. 필드 직접 접근
     * 3. 생성자 사용
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
    public void findDtoByField(){ //setter 없어도 상관 X

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
    public void findDtoByConstructor(){ //setter 없어도 상관 X

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
    public void findMyDtoByField(){ //setter 없어도 상관 X

        List<MyMemberDto> result = queryFactory
                .select(Projections.fields(MyMemberDto.class,
                        member.username.as("name"), //별칭이 다를 때
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
                        member.username.as("name"), //별칭이 다를 때

                        //서브쿼리에 별칭 적용
                        ExpressionUtils.as(JPAExpressions
                        .select(memberSub.age.max())
                        .from(memberSub), "age")
                ))
                .from(member)
                .fetch();

        System.out.println("result " + result);
    }


    @Test
    public void findMyDtoByConstructor(){ //setter 없어도 상관 X

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
        //construtor 와의 차이점은 컴파일 시점에서, 에러 체크확인가능

        List<MemberDto> result = queryFactory
                .select(new QMemberDto(member.username, member.age)) //cmd + p
                .from(member)
                .fetch();

        System.out.println("result " + result);
        //단점은 dto가 querydsl에 의존적이게 됨.
    }





}

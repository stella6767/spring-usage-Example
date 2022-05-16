package com.example.jpademo.domain.member;

import com.example.jpademo.domain.team.QTeam;
import com.example.jpademo.dto.MemberSearchCondition;
import com.example.jpademo.dto.MemberTeamDto;
import com.example.jpademo.dto.QMemberTeamDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static com.example.jpademo.domain.member.QMember.*;
import static com.example.jpademo.domain.team.QTeam.*;
import static org.springframework.util.StringUtils.*;

//실제 쓸 때는 서비스단에서 trancstional 붙여주자.
@Repository
public class MemberJpaRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public MemberJpaRepository(EntityManager em, JPAQueryFactory queryFactory) {
        this.em = em;
        this.queryFactory = queryFactory;
    }

    public void save(Member member){
        em.persist(member);
    }

    public Optional<Member> findById(Long id){
        Member findMember = em.find(Member.class, id);
        return Optional.ofNullable(findMember);
    }

    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findAllQueryDsl(){
        return queryFactory
                .selectFrom(member)
                .fetch();
    }

    public List<Member> findByUsename(String username){
        return em.createQuery("select m from Member m where m.username = :username", Member.class)
                .setParameter("username",username)
                .getResultList();
    }

    public List<Member> findByUsenameQueryDsl(String username){
        return queryFactory
                .selectFrom(member)
                .where(member.username.eq(username))
                .fetch();
    }


    public List<MemberTeamDto> searchByBuilder(MemberSearchCondition condition){

        BooleanBuilder builder = new BooleanBuilder();

        //cmd + shift + enter
        if (hasText(condition.getUsername())) {
            builder.and(member.username.eq(condition.getUsername()));
        }

        if (hasText(condition.getTeamName())){
            builder.and(team.name.eq(condition.getTeamName()));
        }

        if (condition.getAgeGoe() != null){
            builder.and(member.age.goe(condition.getAgeGoe()));
        }
        if (condition.getAgeLoe() != null){
            builder.and(member.age.loe(condition.getAgeLoe()));
        }


        return queryFactory
                .select(new QMemberTeamDto(
                        member.id.as("memberId"),
                        member.username,
                        member.age,
                        team.id.as("teamId"),
                        team.name.as("teamName")
                ))
                .from(member)
                .leftJoin(member.team, team)
                .where(builder)
                .fetch();

    }


    public List<MemberTeamDto> search(MemberSearchCondition condition){
        

        return queryFactory
                .select(new QMemberTeamDto(
                        member.id.as("memberId"),
                        member.username,
                        member.age,
                        team.id.as("teamId"),
                        team.name.as("teamName")
                ))
                .from(member)
                .leftJoin(member.team, team)
                .where(
                        usernameEq(condition.getUsername()),
                        teamNameEq(condition.getTeamName()),
                        ageGoe(condition.getAgeGoe()) ,
                        ageLoe(condition.getAgeLoe())
                       // ageBetween(condition.getAgeLoe(), condition.getAgeGoe())
                        
                )
                .fetch();

    }

    private BooleanExpression ageBetween(int ageLoe, int ageGoe){ //null check 해줘야 됨.
        return ageLoe(ageLoe).and(ageGoe(ageGoe));
    }

    private BooleanExpression ageLoe(Integer ageLoe) { //predicate 가 아닌 BooleanExpression 이면 조합이 가능함
        return ageLoe != null  ? member.age.loe(ageLoe) : null;
    }

    private BooleanExpression ageGoe(Integer ageGoe) {
        return ageGoe!=null ? member.age.goe(ageGoe) : null;
    }

    private BooleanExpression teamNameEq(String teamName) {
        return hasText(teamName) ? team.name.eq(teamName) : null;
    }

    private BooleanExpression usernameEq(String username) {
        return hasText(username) ? member.username.eq(username) : null;
    }

}

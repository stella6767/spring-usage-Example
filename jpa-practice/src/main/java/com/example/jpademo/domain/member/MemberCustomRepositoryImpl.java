package com.example.jpademo.domain.member;

import com.example.jpademo.dto.MemberSearchCondition;
import com.example.jpademo.dto.MemberTeamDto;
import com.example.jpademo.dto.QMemberTeamDto;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.jpademo.domain.member.QMember.*;
import static com.example.jpademo.domain.team.QTeam.team;
import static org.springframework.util.StringUtils.hasText;

//Impl 네이밍 규칙
@RequiredArgsConstructor
@Repository  //QueryDsl 용도
public class MemberCustomRepositoryImpl implements MemberCustomRepository {

    private final JPAQueryFactory queryFactory;



    @Override
    public List<Member> findMemberByNameIsNull() {

        return queryFactory
                .selectFrom(member)
                .where(member.username.isNull())
                .fetch();
    }

    @Override
    public List<MemberTeamDto> search(MemberSearchCondition condition) {
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

    @Override
    public Page<MemberTeamDto> searchPageSimple(MemberSearchCondition condition, Pageable pageable) {
        QueryResults<MemberTeamDto> result = queryFactory
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
                        ageGoe(condition.getAgeGoe()),
                        ageLoe(condition.getAgeLoe())
                        // ageBetween(condition.getAgeLoe(), condition.getAgeGoe())

                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        //return result.getResults();

        long total = result.getTotal();
        List<MemberTeamDto> content = result.getResults();
//
        return new PageImpl<>(content, pageable, total);


    }

    @Override
    public Page<MemberTeamDto> searchPageComplex(MemberSearchCondition condition, Pageable pageable) {
        List<MemberTeamDto> content = getMemberTeamDtos(condition, pageable);
        //return result.getResults();

        //long total = getTotal(condition); //extract method/ alt + enter


        JPAQuery<Member> countQuery = queryFactory
                .select(member)
                .from(member)
                .leftJoin(member.team, team)
                .where(
                        usernameEq(condition.getUsername()),
                        teamNameEq(condition.getTeamName()),
                        ageGoe(condition.getAgeGoe()),
                        ageLoe(condition.getAgeLoe())
                );

        //return new PageImpl<>(content, pageable, total);

        //content size가 PAGE size 보다 작을 때 굳이 total count query를 날리지 않는다.
        //return PageableExecutionUtils.getPage(content, pageable, ()-> countQuery.fetchCount());
        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);

    }

    private long getTotal(MemberSearchCondition condition) {
        return queryFactory
                .select(member)
                .from(member)
                .leftJoin(member.team, team)
                .where(
                        usernameEq(condition.getUsername()),
                        teamNameEq(condition.getTeamName()),
                        ageGoe(condition.getAgeGoe()),
                        ageLoe(condition.getAgeLoe())
                )
                .fetchCount();
    }

    private List<MemberTeamDto> getMemberTeamDtos(MemberSearchCondition condition, Pageable pageable) {
        List<MemberTeamDto> content = queryFactory
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
                        ageGoe(condition.getAgeGoe()),
                        ageLoe(condition.getAgeLoe())
                        // ageBetween(condition.getAgeLoe(), condition.getAgeGoe())

                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        return content;
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

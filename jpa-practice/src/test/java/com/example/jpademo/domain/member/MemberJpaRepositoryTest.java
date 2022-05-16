package com.example.jpademo.domain.member;

import com.example.jpademo.domain.team.Team;
import com.example.jpademo.dto.MemberSearchCondition;
import com.example.jpademo.dto.MemberTeamDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
class MemberJpaRepositoryTest {


    @Autowired
    EntityManager em;

    @Autowired
    MemberJpaRepository memberJpaRepository;

    @Test
    public void basicTest(){
        Member member = new Member("member1", 10);
        memberJpaRepository.save(member);

        Member findMember = memberJpaRepository.findById(member.getId()).get();
        assertThat(findMember).isEqualTo(member);

        List<Member> result1 = memberJpaRepository.findAll();
        assertThat(result1).containsExactly(member);

        List<Member> result2 = memberJpaRepository.findByUsename("member1");
        assertThat(result2).containsExactly(member);
    }



    @Test
    public void basicTest2(){
        Member member = new Member("member1", 10);
        memberJpaRepository.save(member);



        List<Member> result1 = memberJpaRepository.findAllQueryDsl();
        assertThat(result1).containsExactly(member);

        List<Member> result2 = memberJpaRepository.findByUsenameQueryDsl("member1");
        assertThat(result2).containsExactly(member);
    }



    @Test
    public void searchTest(){

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

        MemberSearchCondition condition = new MemberSearchCondition();
        //condition.setAgeGoe(35);
        //condition.setAgeLoe(40);
        //condition.setTeamName("teamB"); //조건이 없으면 다 끌고온다.

        List<MemberTeamDto> result = memberJpaRepository.searchByBuilder(condition);

        //assertThat(result).extracting("username").containsExactly("member3","member4");
        assertThat(result).extracting("username").containsExactly("member4");

    }

    @Test
    public void search(){

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

        MemberSearchCondition condition = new MemberSearchCondition();
        condition.setAgeGoe(35);
        condition.setAgeLoe(40);
        //condition.setTeamName("teamB"); //조건이 없으면 다 끌고온다.

        List<MemberTeamDto> result = memberJpaRepository.search(condition);

        //assertThat(result).extracting("username").containsExactly("member3","member4");
        assertThat(result).extracting("username").containsExactly("member4");

    }

}
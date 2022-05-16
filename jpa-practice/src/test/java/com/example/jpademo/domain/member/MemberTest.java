package com.example.jpademo.domain.member;

import com.example.jpademo.domain.team.Team;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;



@SpringBootTest
@Transactional //기본적으로 JPA는 transaction을 기반으로 작동하니 무조건 붙여줘야됨.
//@Commit
class MemberTest {

    @Autowired
    EntityManager em;


    private static final Logger log = LoggerFactory.getLogger(MemberTest.class);

    @Test
    public void testEntity(){

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

}
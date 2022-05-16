package com.example.jpademo.config;


import com.example.jpademo.domain.member.Member;
import com.example.jpademo.domain.team.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Profile("local")
@Component
@RequiredArgsConstructor
public class InitMember {

    private final InitMemberService initMemberService;

    @PostConstruct //PostConstruct 와 Transactional을 같이 넣을 수 없다.
    public void init(){
        initMemberService.init();
    }


    @Component
    static class InitMemberService{
        @PersistenceContext
        private EntityManager em;

        @Transactional
        public void init(){
            Team teamA = new Team("teamA");
            Team teamB = new Team("teamb");
            em.persist(teamA);
            em.persist(teamB);

            for (int i=0; i<100; i++){
                Team selectedTeam = i%2==0?teamA:teamB;
                em.persist(new Member("member" + i, i, selectedTeam));
            }

        }


    }
}

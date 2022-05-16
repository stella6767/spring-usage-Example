package com.example.jpademo.domain.team;


import com.example.jpademo.domain.member.Member;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@ToString(of = {"id","name"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
@Entity
public class Team {

    @Id //Pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Table, auto_increment, Sequence
    private Long id;

    private String name;

    //1:N 관계에서는 1이 외래키의 주인이 아니므로, mappedBy로 외래키의 주인이 아니라는 걸 알림!
    //영속성 전이! 팀을 저장할때, 맴버도 같이 영속화. CascadeType.remove는 부모 객체를 삭제하면 자식 객체도 삭제
    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Member> members = new ArrayList<>(); //주인이 아닌 쪽이므로, 읽기만 할 수 있다.

    /**
     *  CascadeType.ALL, orphanRemoval = true
     *  두 옵션을 모두 활성화하면, 팀 엔티티를 통해서 멤버 엔티티의 생명주기를 관리할 수 있다.
     * 자식을 저장하려면 부모에 등록만 하면 된다.
     * 자식을 삭제하려면 부모에서 제거하면된다.
     */


    public Team(String name) {
        this.name = name;
    }

    public void addMember(Member member){
        this.members.add(member);
    //양쪽에 다 작성하면 둘 중 하나만 호출하면 된다.
        if(member.getTeam() != this){//무한루프에 빠지는지 체크
            member.setTeam(this);
        }
    }

}


/**
 * JPA는 부모 엔티티와 연관관계가 끊어진 자식 엔티티를 자동으로 삭제하는 기능 제공하는데
 * 이것을 고아 객체 제거라 한다. 이 기능을 사용하면, 부모엔티티의 컬렉션에서 자식 엔티티의 참조만
 * 제거하면 자식 엔티티가 자동으로 삭제되도록 할 수 있다.
 * 
 * 고아 객체 제거는 참조가 제거된 엔티티는 다른 곳에서 참조하지 않는 고아 객체로 보고 삭제하는 기능이다
 * 따라서 참조하는 곳이 하나일 때만 사용해야 한다.
 */
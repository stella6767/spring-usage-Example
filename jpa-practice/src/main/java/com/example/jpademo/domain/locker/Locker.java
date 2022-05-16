package com.example.jpademo.domain.locker;

import com.example.jpademo.domain.member.Member;
import lombok.*;

import javax.persistence.*;

@ToString(of = {"id","name"}) //연관관계 필드는 제외한다. 무한루프에 빠지지 않도록
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
public class Locker { //대상 테이블


    @Id //Pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Table, auto_increment, Sequence
    @Column(name="locker_id")
    private Long id;

    private String name;


    /**
     * 일대일 관계에서는 어느 쪽이든 외래키를 가질 수 있다.
     * 따라서 누가 외래키를 가질지 선택해야 한다.
     * 주 테이블에 외래키 = 객체지향개발자 선호
     * 대상 테이블에 외래키 = DBA 가 선호, 테이블 관계를 일대다로 변경할떄, 테이블 구조를 그대로 유지가능
     */

    /**
     * 프록시를 사용할 때 외래 키를 직접 관리하지 않는 일대일 관계는 지연 로딩으로 설정해도 즉시 로딩
     * 프록시 대신 bytecode instrumentation을 사용하여 해결할 수 있다.
     */

    @OneToOne(mappedBy = "locker") //주 테이블의 자기 필드명
    private Member member;

}

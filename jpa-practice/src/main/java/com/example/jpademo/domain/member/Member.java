package com.example.jpademo.domain.member;


import com.example.jpademo.domain.locker.Locker;
import com.example.jpademo.domain.memberproduct.MemberProduct;
import com.example.jpademo.domain.order.Order;
import com.example.jpademo.domain.product.Product;
import com.example.jpademo.domain.team.Team;
import com.example.jpademo.utills.BooleanToYNConverter;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Convert(converter = BooleanToYNConverter.class, attributeName = "vip")
@ToString(of = {"id","username","age"}) //연관관계 필드는 제외한다. 무한루프에 빠지지 않도록
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
@Entity
public class Member { //주 테이블

    @Id //Pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Table, auto_increment, Sequence
    @Column(name="member_id")
    private Long id;

    private String username;

    private Integer age;

    /**
     * 일대다 양방향 VS 다대일 양방향
     * 둘은 사실 같은 말이지만, 왼쪽을 연관관계의 주인으로 치고 구분한다면
     * 항상 다대일 양방향 맵핑을 하자. (예외는 있을 수 있음)
     * @param username
     */

    @ManyToOne(fetch = FetchType.EAGER) //즉시로딩전략, 컬렉션 객체가 아니면 그냥 eager 쓰자
    @JoinColumn(name = "team_id") //외래키의 주인! joinCoulumn을 안 쓰면, JPA는 연결테이블을 중간에 두고 연관관계를
    private Team team; //관리하는 조인 테이블 전략을 기본으로 사용



    /**
     * 일대일 관계에서는 어느 쪽이든 외래키를 가질 수 있다.
     * 따라서 누가 외래키를 가질지 선택해야 한다.
     * 주 테이블에 외래키 = 객체지향개발자 선호
     * 대상 테이블에 외래키 = DBA 가 선호, 테이블 관계를 일대다로 변경할떄, 테이블 구조를 그대로 유지가능
     */

    /**
     * 외래키에 null을 허용하는 관계를 선택적 비식별
     * 외래키에 not null 설정을 적용해주면, 내부 조인 쿼리 생성. nullable=false
     */

    @OneToOne //주 테이블의 자기 필드명
    @JoinColumn(name = "locker_id", nullable = true) //연관관계의 주인
    private Locker locker;



    /**
     * 관계형 db에서는 정규화된 테이블 2개로 다대다 관계를 표현할 수 없다.
     * 그래서 연결 테이블을 사용. @ManyToMany를 사용하면 간단하게 연결테이블을 만들 수 있지만
     * 연결테이블에 필요한 칼럼을 추가할 수 없기 때문에 실무에서 사용하기는 한계점이 있음
     */
//    @ManyToMany
//    @JoinTable(name = "member_product",
//    joinColumns = @JoinColumn(name = "member_id"),
//    inverseJoinColumns = @JoinColumn(name = "product_id"))
//    private List<Product> products = new ArrayList<>();

    
//    @OneToMany(mappedBy = "member") //역방향
//    private List<MemberProduct> memberProducts;


    /**
     * 식별관계: 받아온 식별자를 기본 키 + 외래 키로 사용
     * 비식별 관계: 받아온 식별자는 외래키로만 사용하고, 새로운 식별자를 추가. (추천)
      */

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY) //역방향
    //@OrderColumn(name = "position") //순서가 있는 특수한 컬렉션으로 인식, DB에 순서 값을 저장해서 조회할 때 사용, 실무에서 잘 안 쓰임
    //@OrderBy("orderAmount desc, id asc") //이건 사용
    private List<Order> orders;




    //복합 값 타입

    @Embedded
    @AttributeOverrides({  //임베디드 타입에 정의한 매핑정보를 재정의
            @AttributeOverride(name = "city", column = @Column(name = "company_city")),
            @AttributeOverride(name = "street", column = @Column(name = "company_street"))
    })
    private Address address;

    /**
     *  임베디드 타입이 null 이면 매핑한 칼럼 값 모두 null 이 된다.
     * 임베디드 타입 같은 값 타입을 여러 엔티티에서 공유하면 위험하다.
     * 값 타입을 하나 이상 저장하려면,컬렉션에 보관하고 @ElementCollection @CollectionTable
     * 를 사용하면 된다.
     */


    private boolean vip;

    public Member(String username) {
        this(username, 0);
    }

    public Member(String username, Integer age) {
        this(username, age, null);
    }

    public Member(String username, Integer age, Team team){
        this.username = username;
        this.age = age;

        if(team != null){
            changeTeam(team);
        }
    }


    public void changeTeam(Team team){
        this.team = team;
        team.getMembers().add(this);
    }

    public void printMember(){

        /**
         * 회원 엔티티를 조회할 때, 회원과 연관된 팀 엔티티까지 데이터베이스에서
         * 함께 조회하는 것은 비효율적
         * JPA는 이런 문제를 해결하려고, 엔티티가 실제 사용될 때까지 DB 조회를 지연하는
         * 방법을 제공(lazy loading) 팀 엔티티 값을 실제 사용하는 시점에서 데이터를 조회
         * 지연 로딩 기능을 사용하려면, 실제 엔티티 객체 대신 가짜 객체가 필요한데, 이것을 프록시 객체라 한다
         */



        System.out.println("회원 이름 " + this.getUsername());
    }





}


/**
 * JPA 기본 패치 전략
 * @ManyToOne, @OneToOne : 즉시 로딩
 * @OneToMany, @ManyToMany: 지연 로딩
 * 추천하는 방법은 모든 연관관계에 일단은 지연로딩하고, 추후 상황봐가며 변경
 */

/**
 * 엔티티타입특징
 * 1. 식별자가 있다. @id
 * 2. 생명주기가 있다.
 * 3. 공유할 수 있다.
 * 값 타입 특징
 * 1. 식별자가 없다
 * 2. 생명 주기를 엔티티에 의존한다
 * 3. 공유하지 않는 것이 안전하다.
 */


/**
 * JPA 의 글로벌 패치 전략은 기본값은 다음과 같다.
 * @OneToOne, @ManyToOne: 기본 패치 전략은 즉시 로딩
 * @OneToMany, @ManyToMany: 기본 패치 전략은 지연 로딩
 */
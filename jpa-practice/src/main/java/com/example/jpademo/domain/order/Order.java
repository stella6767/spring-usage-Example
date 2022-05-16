package com.example.jpademo.domain.order;


import com.example.jpademo.domain.member.Member;
import com.example.jpademo.domain.product.Product;

import javax.persistence.*;


/**
 * lazy loading이면 조회할 엔티티에 따라 jpql fetch join을 따로 사용해야한다.
 * 엔티티 그래프 기능을 사용하면, 엔티티를 조회하는 시점에서 함께 조회할 연관엔티티를 선택할 수 있다.
 * 따라서 jpql은 데이터를 조회하는 기능만 수행하면 되고, 연관된 엔티티를 함께 수행하는 기능은 엔티티 
 * 그래프를 사용하면 된다. subgraphs를 활용하면,연관관계 엔티티가 참조하는 엔티티까지 함께 조회가능
 */

@NamedEntityGraph(name = "Order.withMember", attributeNodes = {
        @NamedAttributeNode("member")
})
@Entity
public class Order {

    /**
     *  다대다 새로운 기본 키 사용
     */


    @Id //Pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Table, auto_increment, Sequence
    @Column(name="order_id")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id")
    private Member member;//MemberProductId.member와 연결

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product; //MemberProductId.product와 연결

    private Integer orderAmount;

}

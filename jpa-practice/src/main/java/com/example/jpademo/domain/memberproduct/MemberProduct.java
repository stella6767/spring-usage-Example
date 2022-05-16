package com.example.jpademo.domain.memberproduct;


import com.example.jpademo.domain.member.Member;
import com.example.jpademo.domain.product.Product;

import javax.persistence.*;

@Entity
//@IdClass(MemberProductId.class)
public class MemberProduct {


    /**
     * id class를 사용할 때는 식별자 클래스의 속성명과 엔티티에서 사용하는 식별자의 속성명이 같아야함
     * serializable 인터페이스를 구현해야 함
     * equals. hascode 구현해야함
     * 기본 생성자가 있어야 함
     * 식별자 클래스는 public
     */

//    @Id
//    @ManyToOne
//    @JoinColumn(name = "member_id")
//    private Member member;//MemberProductId.member와 연결
//
//
//    @Id
//    @ManyToOne
//    @JoinColumn(name = "product_id")
//    private Product product; //MemberProductId.product와 연결

    @EmbeddedId
    private MemberProductId2 memberProductId2;

    private Integer orderAmount;
}

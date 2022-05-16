package com.example.jpademo.domain.product;

import com.example.jpademo.domain.member.Member;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@ToString(of = {"id","name"}) //연관관계 필드는 제외한다. 무한루프에 빠지지 않도록
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
public class Product {

    @Id //Pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Table, auto_increment, Sequence
    @Column(name="product_id")
    private Long id;

    private String name;

//    @ManyToMany(mappedBy = "products") //역방향 추가
//    private List<Member> members = new ArrayList<>();
    
    //굳이 객체 그래프 탐색 기능이 필요하지 않다 판단돼서, order 연관관계 만들지 않음
    

}

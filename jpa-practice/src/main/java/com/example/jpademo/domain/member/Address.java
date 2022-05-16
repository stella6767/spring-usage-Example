package com.example.jpademo.domain.member;


import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.util.Objects;

@Getter
@Embeddable //복합값타입, 더욱 객체지향적으로 설계하자
public class Address {

    @Column(name = "city")
    private String city;

    private String street;

    private String zipcode;


    /**
     * @Embeddable: 값 타입을 정의하는 곳에 표시
     * @Embedded: 값 타입을 사용하는 곳에 표시
     * 기본 생성자가 필수: 이건 정말 기본적인 것이지만 생성자 생략해도 기본 생성자 자동으로 만들어줌
     * 다만 다른 생성자를 만들었으면, 기본 생성자 직접 만들어야 함.
     * side effect를 만들기 위해 수정자(setter)는 만들지 않는다.
     */

    //@Embedded
    //private 뭐시기 땡떙 //임베디드 타입 안에도 임베디드 타입을 포함시킬 수 있다. 또는 다른 엔티티 타입을 참조할 수 있다. @ManyToOne

//    public Boolean isHomeInSeoul(){
//
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(city, address.city) && Objects.equals(street, address.street) && Objects.equals(zipcode, address.zipcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, street, zipcode);
    }
}


/**
 * 자바가 제공하는 객체 비교는 2가지다.
 * 동일성 비교: 인스턴스의 참조 값을 비교, == 사용
 * 동등성 비교: 인스턴스의 값을 비교, equals() 사용
 * 값 타입은 비록 인스턴스가 달라도 그 안에 값이 같으면 같은 것으로 봐야 된다. 따라서 equals
 */
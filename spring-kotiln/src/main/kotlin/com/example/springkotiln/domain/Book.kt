package com.example.springkotiln.domain

import javax.persistence.*

//public default

@Entity
public class Book(

        /**
         * var vs val
         * var = 가변
         * val = 불변 // 초기화해주지 않은 상태에 한 해서 최초 한 번은 값을 할당해줄 수 있음.
         *
         * 둘 다 타입추론을 지원하는데, 값이 할당되어있지 않으면 타입추론을 할 수 없으므로, 뒤에 타입을 명시해줘야 된다.
         * 물론 불변이라도 자바와 마찬가지로 내부적인 값들도 불변이 아니면 완전한 불변성을 보장할 수 없다.
         *
         * 코틀린은 원시타입과 래퍼런스 타입을 따로 구분하지 않고, 지가 알아서 판단해줌.
         */



        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,  //null이 들어올 수 있으면 ? 붙여주라

        @Column
        val name: String





)
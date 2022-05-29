package com.example.springkotiln.util

import mu.KLogger
import mu.KotlinLogging

//private val logger = KLogging()

/**
 * 생소하지만 () 가 constructor
 * getter, setter 생략가능, 코틀린은 모든 맴버변수는 자동 프로퍼티
 *
 * 생성자 오버로딩을 지원하긴 하는데, 그것보다는, 정적팩토리패턴이나, 디펄트 아큐먼트를 활용
 *
 *
 */


class Person(
        val name: String = "강민규",
        var age: Int = 1,
        private val logger: KLogger = KotlinLogging.logger {}
) {


    //private val log: Logger? = LoggerFactory.getLogger(this::class.java)



    //companion object : KLogging()

    //T.logger

    constructor(name: String):this(name, 1){
        //logger.info("첫번째 부생성자")
        logger?.info("첫번째 부생성자")
    }

    constructor(): this("강민규"){
        println("두번째 부생성자")
    }
    
    
    init {
        if (age <= 0) {
            throw IllegalArgumentException("나이는 ${age} 일 수 없습니다.")
        }

        logger?.info("초기화 블록")
    }


    /**
     * custom getter
     */

    val isAdult: Boolean
        get() {
            return this.age >= 20
        }


}


fun main() {

    val person = Person()

    //person.name = "변경불가능"



    println("person age는 ${person.age} 입니다.")



}
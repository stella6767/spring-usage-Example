package com.example.springkotiln.test

import mu.KLogging
import org.slf4j.Logger
import org.slf4j.LoggerFactory

private val logger = KLogging()


class Person(
        val name: String = "강민규",
        var age: Int = 1
) {


    private val log: Logger? = LoggerFactory.getLogger(this::class.java)

    companion object : KLogging()

    //T.logger

    constructor(name: String):this(name, 1){
        logger.info("첫번째 부생성자")
    }

    constructor(): this("강민규"){
        println("두번째 부생성자")
    }
    
    
    init {
        if (age <= 0) {
            throw IllegalArgumentException("나이는 ${age} 일 수 없습니다.")
        }

        log?.info("초기화 블록")
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
    println("person age는 ${person.age} 입니다.")



}
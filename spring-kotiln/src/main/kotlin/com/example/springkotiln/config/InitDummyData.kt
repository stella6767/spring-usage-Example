package com.example.springkotiln.config

import com.example.springkotiln.domain.BookRepository
import mu.KotlinLogging
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Configuration
import java.util.*



@Configuration
class InitDummyData (
        private val bookRepository: BookRepository,
) : CommandLineRunner{



    /**
     * 생성자에서 해줄 로직을 init 키워드 바디 안에 선언할 수 있다.
     */




    init {
        //log?.info("init block")
    }

    override fun run(vararg args: String?) {
        TODO("Not yet implemented")


        val asList = Arrays.asList(1, 2, 3, 4)



    }


    //constructor() : this(bookRepository) //부생성자 보다는 default parameter 나 정적 팩토리 메서드 추천


    private fun insertDummyItems():Unit {


    }


}
package com.example.springkotiln

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringKotilnApplication



fun main(args: Array<String>) {

    println("kotiln spring start")

    runApplication<SpringKotilnApplication>(*args)
}

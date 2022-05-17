package com.example.springkotiln.web

import com.example.springkotiln.service.BookService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class BookController(
        private val bookService: BookService
) {



    @GetMapping("/hello")
    private fun helloWorld(): String {

        return "hello world";
    }




}
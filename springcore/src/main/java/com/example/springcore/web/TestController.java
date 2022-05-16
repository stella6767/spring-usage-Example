package com.example.springcore.web;

import com.example.springcore.domain.Bird;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

@RestController
public class TestController {


    private final WebApplicationContext context;


    public TestController(WebApplicationContext context) {
        this.context = context;
    }

    @GetMapping("/test")
    public void test(){
        Bird bird = context.getBean("bird", Bird.class);
        Bird bird2 = context.getBean("bird", Bird.class);

        System.out.println("bird1: " + bird);
        System.out.println("bird2: " + bird2);
    }


}

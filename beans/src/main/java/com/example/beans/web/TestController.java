package com.example.beans.web;


import com.example.beans.dto.Fish;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

@RestController
public class TestController {

    //RequestScope vs PrototypeScope

    //private final Fish fish;

    private final WebApplicationContext context;

    public TestController(WebApplicationContext context) {
        this.context = context;
    }

//    public TestController(Fish fish, WebApplicationContext context) {
//        this.fish = fish;
//        this.context = context;
//    }


    @GetMapping("/test")
    public void test(){

        Fish fish = context.getBean("fish",Fish.class);
        Fish fish2 = context.getBean("fish",Fish.class);

        System.out.println("fish1: " + fish.toString());
        System.out.println("fish2:  " + fish2.toString());

    }

}

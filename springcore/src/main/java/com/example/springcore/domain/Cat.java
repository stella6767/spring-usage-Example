package com.example.springcore.domain;


import com.example.exclude.OutSideDog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Cat implements Animal{

    private final OutSideDog outSideDog;

    private final Turtle turtle;

    private static final Logger log = LoggerFactory.getLogger(Cat.class);


//    public Cat() {
//
//        log.info("???1");
//    }

    public Cat(OutSideDog outSideDog, Turtle turtle) {
        this.turtle = turtle;
        log.info("???2");
        this.outSideDog = outSideDog;
    }

    @PostConstruct
    public void ss(){
        outSideDog.test();
    }



    @Override
    public void bark() {

        log.info("야옹");
    }
}

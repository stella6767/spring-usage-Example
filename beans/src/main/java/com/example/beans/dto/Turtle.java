package com.example.beans.dto;

import com.example.beans.dto2.Bear;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

//@RestController //이름 기본전략은싱글톤.
public class Turtle {

    private static final Logger LOGGER = LoggerFactory.getLogger(Turtle.class);


    @Autowired
    private Bear bear;

    public Turtle() {
        LOGGER.info(this.toString() + " 등록!");
    }


    //private String name;

    public void bark(){
        LOGGER.info("멍멍");
    }


    //class를 하나 만들었죠. 여기서 ioc 개념에 대해서 진짜 초간단하게 알아볼게요.
    //제어의 역전을 뜻하는 거죠.
    //이제는 bean들을 등록하는 다양한 방법들에 대해서 알아볼게요.

}

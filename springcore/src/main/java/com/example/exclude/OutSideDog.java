package com.example.exclude;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;



@Configuration
public class OutSideDog {

    private static final Logger log = LoggerFactory.getLogger(OutSideDog.class);

    public OutSideDog() {
        //System.out.println("어플리케이션 구동 시점에서 function으로 bean 등록");
        log.info("outside dog bean 등록");
    }


    public void test(){
        log.info("흑흑");
    }
}


//자동 빈 등록보다는 수동 빈 등록이 우선권을 가진다. 수동 빈이 자동 빈을 오버라이딩해버린다.


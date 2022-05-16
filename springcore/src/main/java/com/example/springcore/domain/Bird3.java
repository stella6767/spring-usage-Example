package com.example.springcore.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


//@DependsOn(value = {"dog"})
@Repository
//@Order(value= Ordered.LOWEST_PRECEDENCE)
public class Bird3 implements Animal{

    private static final Logger log = LoggerFactory.getLogger(Bird3.class);

    public Bird3() {
        log.info("Bird3 생성자 호출");
    }

    @Override
    public void bark() {
      log.info("짹짹");
    }
}

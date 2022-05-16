package com.example.springcore.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


//@DependsOn(value = {"dog"})
@Service
//@Order(value= Ordered.LOWEST_PRECEDENCE)
public class Bird2 implements Animal{

    private static final Logger log = LoggerFactory.getLogger(Bird2.class);

    public Bird2() {
        log.info("Bird2 생성자 호출");
    }

    @Override
    public void bark() {
      log.info("짹짹");
    }
}

package com.example.springcore.domain;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Scope("request")
//@DependsOn(value = {"dog"})
@Component
//@Order(value= Ordered.LOWEST_PRECEDENCE)
public class Bird implements Animal{

    private static final Logger log = LoggerFactory.getLogger(Bird.class);

    public Bird() {
        log.info("Bird 생성자 호출");
    }

    @Override
    public void bark() {
      log.info("짹짹");
    }
}

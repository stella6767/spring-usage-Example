package com.example.springcore.domain;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
public class Horse {

    public Horse() {
        log.info(this.toString() + " 등록");
    }
}

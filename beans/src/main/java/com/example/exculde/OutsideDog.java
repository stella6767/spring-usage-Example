package com.example.exculde;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OutsideDog {

    public OutsideDog() {

        log.info(this.toString() + " 등록");
    }
}

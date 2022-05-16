package com.example.springevent.handler;

import com.example.springevent.event.MyEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class MyAnotherEventHandler {



    @EventListener
    @Async
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public void handle(MyEvent myEvent){

        System.out.println(Thread.currentThread().getName());
        log.info("Another => {}", myEvent.getSource());
    }

    @EventListener
    @Async
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public void handle(ContextRefreshedEvent myEvent){

        System.out.println(Thread.currentThread().getName());
        log.info("Another => {}", myEvent.getSource());
    }
}

package com.example.springevent.handler;

import com.example.springevent.event.MyEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class MyEventHandler {


    @EventListener
    @Order(Ordered.HIGHEST_PRECEDENCE + 2)
    public void onApplicationEvent(MyEvent event){
        System.out.println(Thread.currentThread().getName());
        log.info("이벤트 받았다 데이터는 " + event.getSource());
    }

}

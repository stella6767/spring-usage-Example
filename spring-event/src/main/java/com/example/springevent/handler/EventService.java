package com.example.springevent.handler;


import com.example.springevent.event.MyEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.availability.ApplicationAvailability;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EventService {

    private final ApplicationEventPublisher eventPublisher;

    private final ApplicationAvailability applicationAvailability;

    public EventService(ApplicationEventPublisher eventPublisher, ApplicationAvailability applicationAvailability) {
        this.eventPublisher = eventPublisher;
        this.applicationAvailability = applicationAvailability;
    }

    public void event발생(){

        eventPublisher.publishEvent(new MyEvent(100, this));

    }


}

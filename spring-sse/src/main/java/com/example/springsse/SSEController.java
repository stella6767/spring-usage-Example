package com.example.springsse;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
public class SSEController {


    private List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    //method for client subscription
    @GetMapping("/subscribe")
    public SseEmitter subscribe(){

        SseEmitter sseEmitter = new SseEmitter();

        try {
            sseEmitter.send(SseEmitter.event().name("INIT"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        emitters.add(sseEmitter);

        sseEmitter.onCompletion(() -> emitters.remove(sseEmitter));
        sseEmitter.onTimeout(() -> emitters.remove(sseEmitter));
        sseEmitter.onError((e) -> emitters.remove(sseEmitter));


        return sseEmitter;
    }

    //method for dispatching events to all clients
    @GetMapping(value = "/dispatchEvent")
    public void dispatchEventToClients(@RequestParam String freshNews){

        for (SseEmitter emitter:emitters) {
            try {
                emitter.send(SseEmitter.event().name("latestNews").data(freshNews));
            } catch (IOException e) {

                emitters.remove(emitter);
                e.printStackTrace();
            }

        }

    }


}

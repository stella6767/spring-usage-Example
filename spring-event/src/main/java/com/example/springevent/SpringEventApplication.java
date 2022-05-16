package com.example.springevent;

import com.example.springevent.handler.EventService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.Random;


@EnableAsync
@SpringBootApplication
public class SpringEventApplication {




    public static void main(String[] args) {

        if(args.length != 0) {
            System.out.println("args: " + args[0]);
        }


        SpringApplication app = new SpringApplication(SpringEventApplication.class);

        //app.addListeners();

        app.run(args);



        //SpringApplication.run(SpringEventApplication.class, args);
    }

    @Bean
    public CommandLineRunner eventTest(EventService eventService){

        return args -> eventService.event발생();
    }

//    @Bean
//    public CommandLineRunner dataInit(UserRepository userRepository){
//
//        Random random = new Random();
//        return (args -> {
//            for (int i=0; i<100; i++){
//                //System.out.println("i  " + i);
//
//                userRepository.save(new User((long) i, random
//                        .ints(97, 122)
//                        .limit(10)
//                        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
//                        .toString()
//                ));
//            }
//
//        });
//
//    }
}

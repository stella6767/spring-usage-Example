package com.example.springcore.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
//@Profile("local")
@Service("turtle2")
//@Configuration => @RestController or @Controller => @Component    @Repository @Service
public class Turtle {

//    private final Cat cat;

//    public Turtle(Cat cat) {
//        this.cat = cat;
//    }

    public void bark(){
      log.info("ssss");
    }

}

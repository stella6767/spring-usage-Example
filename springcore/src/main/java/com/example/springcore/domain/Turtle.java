package com.example.springcore.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
//@Profile("local")
@Service
//@Configuration => @RestController or @Controller => @Component    @Repository @Service
public class Turtle {


//    private final Cat cat;
//
//    public Turtle(Cat cat) {
//        this.cat = cat;
//    }

    @Autowired
    private Horse horse;

    public void bark(){
      log.info("ttttt");
    }

}

package com.example.beans.dto2;

import com.example.beans.dto.Horse;
import org.springframework.stereotype.Component;

@Component
public class Bear {

//    @Lazy
//    @Autowired
//    private Turtle turtle;
//
//    @Autowired
//    private Snake snake;


    private final Horse horse;

    public Bear(Horse horse) {
        this.horse = horse;

        System.out.println(horse.toString());
    }

    //이런 상황이 생기게끔 설계를 하면 안돼요.

}

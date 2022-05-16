package com.example.beans.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;




//@Profile("prod") //prod
@Controller //이름 기본전략은싱글톤. 그러면 얘네랑 그냥 @Component 무슨 차이냐? 이거는 나중에 restapi 간단하건 만들면서 알아보고,
//저희는 여기서는 그냥 이런 식으로 bean들을 등록시킬 수 있다.
public class Snake {

    private static final Logger LOGGER = LoggerFactory.getLogger(Snake.class);

    public Snake() {
        LOGGER.info(this.toString() + " 등록!");
    }

    //private String name;

    public void bark(){
        LOGGER.info("쌕쌕");
    }


    //class를 하나 만들었죠. 여기서 ioc 개념에 대해서 진짜 초간단하게 알아볼게요.
    //제어의 역전을 뜻하는 거죠.
    //이제는 bean들을 등록하는 다양한 방법들에 대해서 알아볼게요.
    //bean을 등록시키는 방법 중에서는 자동 빈 주입과, 수동으로 빈 주입을 하는 방법이 있거든요,

}

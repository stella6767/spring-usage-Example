package com.example.beans.dto;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Order(1)
@Slf4j
//@DependsOn(value = {"dog"}) //어떨 때 쓰냐면, 내가 만약, 어떤 bean을 주입받아서 쓰고 싶어. 근데 그 bean이 아직 메모리에 올라가 있지 않을 수 있단 말이에요.
@Service("cat") //이름 기본전략은싱글톤.
public class Cat implements Animal{

    //나는 cat이 dog가 먼저 메모리에 띄워진 다음 cat이 메모리에 띄워졌으면 좋겠다.

    private static final Logger LOGGER = LoggerFactory.getLogger(Cat.class);

    public Cat() {
        LOGGER.info(this.toString() + " 등록!");
    }



    @Override
    public void bark() {
      log.info("야옹");
    }


    //class를 하나 만들었죠. 여기서 ioc 개념에 대해서 진짜 초간단하게 알아볼게요.
    //제어의 역전을 뜻하는 거죠.
    //이제는 bean들을 등록하는 다양한 방법들에 대해서 알아볼게요.

}

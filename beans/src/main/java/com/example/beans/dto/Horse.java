package com.example.beans.dto;


import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Slf4j
public class Horse {


    //private final Turtle turtle;

    public Horse() {
        //this.turtle = turtle;
        log.info(this.toString() + " 등록");

        //this.age = 0;
    }

    //private Integer age; //이 상태를 공유해서 그래요.


    //그래서 싱글톤 인스턴스를 만들때는, 이런 상태들을 가지면 안 돼요. 무상태성을 지녀야 되거든요,
    //되도록이면 공유할 수 있는 가능성이 있는 객체들 있죠. 전역적으로, 그런 애들은 thread safe 한지를 꼭 체크하시고,
    //필드가 중복이 될 수 있잖아요. 이러한 상태들을 가지고 있으면 안 돼요.

    //초간단한 예시지만, 결제 시스템이면, 1000원을 예상했는데,
    // 동일한 인스턴스의 동일한 상태를 여러개의 쓰레드가 점유하니까 기대했던 값과 다른 이상한 값들이 떨어져나올 수 있단 말이에요.
    synchronized public void gettingOld(){
//        int n = age;
//        n += 10;
//        age = n;
//        System.out.println(Thread.currentThread().getName()+ " : " + age);
    }

    @PostConstruct
    public void init(){
        System.out.println("말...");
    }


    @PreDestroy
    public void destory(){
        System.out.println("horse 죽음");
    }


}

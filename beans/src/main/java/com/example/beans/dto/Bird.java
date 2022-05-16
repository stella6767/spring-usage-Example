package com.example.beans.dto;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


@Order(2)
@Slf4j
@Repository //이름 기본전략은싱글톤. 알파벳 순, bird라고 컨테이너에 등록될 거에요.
public class Bird implements Animal{

    private static final Logger LOGGER = LoggerFactory.getLogger(Bird.class);

    @Override
    public void bark() {
      log.info("짹짹");

    }


    //class를 하나 만들었죠. 여기서 ioc 개념에 대해서 진짜 초간단하게 알아볼게요.
    //제어의 역전을 뜻하는 거죠.
    //이제는 bean들을 등록하는 다양한 방법들에 대해서 알아볼게요.


}

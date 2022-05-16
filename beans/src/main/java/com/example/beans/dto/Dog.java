package com.example.beans.dto;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


@Slf4j
//@Component//이름 기본전략은싱글톤.
public class Dog implements InitializingBean, DisposableBean, BeanNameAware, ApplicationContextAware {

    //bean의 생명주기에 대해서 알아볼게요.


//    @Autowired
//    private Cat cat; //이 bean을 찾을 수 없음.

    public String name;





    public Dog() {
        log.info(this.toString() + " 등록!"); //1
    }


    @Override
    public void setBeanName(String s) {

        log.info("setBeanName " + s);

    }

    //spring context의 특정 측명을 빈에 주입하는데 사용할 수 있는 추가 Aware인터페이스
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.info("setApplicationContext ");

        Bird bird = applicationContext.getBean("bird", Bird.class);
        //bird.

        //요 정도만 알면, 빈에 대해서 다룰 수 있는 건 아느정도 다 하실 수 있을거에요.
        //그럼 다음에는 이제 restapi 한 번 간단하게 만들어보면서,

    }

    @PostConstruct
    public void start(){
        log.info("postConstrut");//2
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("의존관계 주입이 끝나면 실행");//3
    }


    private void init() {

        log.info("init");//4
    }

    @PreDestroy
    public void destory(){

        log.info("preDestory");//5
    }


    @Override
    public void destroy() throws Exception {

        log.info("어플리케이션이 종료되면서 빈들이 소멸할 때 실행");//6
    }


    private void close() {
        log.info("close"); //7
    }


    public void bark(){
        log.info("멍멍");
    }





    //class를 하나 만들었죠. 여기서 ioc 개념에 대해서 진짜 초간단하게 알아볼게요.
    //제어의 역전을 뜻하는 거죠.
    //이제는 bean들을 등록하는 다양한 방법들에 대해서 알아볼게요.

}

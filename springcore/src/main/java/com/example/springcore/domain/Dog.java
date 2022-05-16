package com.example.springcore.domain;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

//@Component //같은 레벨일 때는 알파벳 순서로 빈이 생성되는 듯.
//@Order(value = Ordered.HIGHEST_PRECEDENCE) //리스트로 컴포넌트로 가져왔을 때 순서를 정해줌.
public class Dog implements Animal, InitializingBean, DisposableBean, BeanNameAware, ApplicationContextAware {


    private static final Logger log = LoggerFactory.getLogger(Dog.class);


    public String name;

    private Integer age;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

//synchronized
     public void gettingOld(){
        int n=age;
        //Thread.yield();//현재 실행 중인 스레드 양보 //일부러 충돌을 발생시키기 위해
        n+=10;
        age = n;
        System.out.println(Thread.currentThread().getName()+" : "+age);


    }

    private final Bird bird;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Dog(Bird bird) {
        this.bird = bird;
        log.info(this.toString()+" 생성자 호출");
        this.age=0;
    }

    @Override
    public void bark() {
        log.info("멍멍");
    }


    @Override
    public void destroy() throws Exception {
        log.info("어플리케이션이 종료되면서 빈들이 소멸될 때 실행");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("의존관계 주입이 끝나면 실행");
    }


    @PostConstruct
    public void start(){
        log.info("start");
    }

    @PreDestroy
    public void destory(){
        log.info("destory");
    }

    public void init(){
        log.info("init");
    }

    public void close(){
        log.info("close");
    }


    //Spring 컨텍스트의 특정 측면을 빈에 주입하는 데 사용할 수 있는 추가 Aware인터페이스 가 있습니다.
    @Override
    public void setBeanName(String s) {
        System.out.println("--- setBeanName executed ---");
        System.out.println(s + "--- create ---");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("--- setApplicationContext executed ---");

        //Bird vird = applicationContext.getBean("bird", Bird.class);

        //dog.name="sss";

    }
}

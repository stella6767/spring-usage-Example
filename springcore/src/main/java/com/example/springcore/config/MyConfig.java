package com.example.springcore.config;

import com.example.springcore.domain.Bird;
import com.example.springcore.domain.Dog;
import com.example.springcore.domain.Horse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;


//@DependsOn(value = {"dog"})
  @Configuration
public class MyConfig {

    private static final Logger log = LoggerFactory.getLogger(MyConfig.class);

//    private final Bird bird;
//    private final Turtle turtle;
//
//
//    public MyConfig(Bird bird, Turtle turtle) {
//        this.turtle = turtle;
//
//        turtle.bark();
//        log.info(this.toString() +" 등록");
//        this.bird = bird;
//
//        bird.bark();
//    }

//value
    @Bean(name = "dog1",initMethod = "init", destroyMethod = "close")
    public Dog mydog(){ //메서드 이름을 자유롭게 지을 수 있다. 스프링 빈이 스프링 코드에 의존하지 않는다. 코드가 아니라 설정정보를 사용하기 때문에 코드를 고칠 수 없는 외부 라이브러리에도 초기화, 종료메서드를 지정할
        return new Dog(bird());
    }


    @Scope("request")
    //@Bean
    public Bird bird(){
        return new Bird();
    }

//    @Bean(value = "dog2",initMethod = "init", destroyMethod = "close")
//    public Dog dog(){
//        return new Dog();
//    }

    @Bean
    public ObjectMapper myMapper(){

        ObjectMapper myMapper = new ObjectMapper();

        log.info(myMapper.toString() + " 등록");
        return myMapper;
    }


    @Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
    @Bean
    public Horse horse(){

        return new Horse();
    }

  //Bean의 라이프싸이클

//    @Bean(initMethod = "init", destroyMethod = "close")
//    public Dog dog(){
//        return new Dog();
//    }
//
//    @Bean(initMethod = "init", destroyMethod = "close")
//    public Dog dog2(){
//        return new Dog();
//    }

}

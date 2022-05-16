package com.example.beans;

import com.example.beans.config.CustomBeanNaming;
import com.example.beans.dto.Dog;
import com.example.exculde.OutsideDog;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.GenericApplicationContext;

@SpringBootApplication
//@ComponentScan(
//        basePackages = "com.example.beans"
//)
public class BeansApplication {

    public static void main(String[] args) {

        SpringApplication.run(BeansApplication.class, args);

//        new SpringApplicationBuilder().sources(BeansApplication.class)
//                .initializers((ApplicationContextInitializer<GenericApplicationContext>) applicationContext -> {
//                    applicationContext.registerBean(OutsideDog.class);
//                }).run(args);


        //Dog dog = new Dog();
        //dog.bark();

        //여기서는 제가 인스턴스를 만들고 그 객체의 메서드를 실행시킨거죠.
        //객체의 주도권이 누구에게 있어요. 개발자에게
        //제어의 역전이라는 것은 이 주도권을 프레임워크에게 넘겨주는 거에요.

    }





}


//제가 디테일한 설명 같은거, 예를 들어, intellij 사용법이나 플러그인, 스프링부트, 스프링레거시의 차이점.. 자바 기초적인 설명
//이런 거 다 건너뛸게요. 제가 전문적인 강사도 아니고, 그냥 제 만족용으로 만드는 영상이기 때문에, 불친절할 수 있어요.
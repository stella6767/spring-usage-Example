package com.example.springcore;

import com.example.springcore.config.CustomBeanNaming;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.support.GenericApplicationContext;

@SpringBootApplication
@ComponentScan(
        basePackages = {"com.example.exclude", "com.example.springcore"}
        //nameGenerator = CustomBeanNaming.class
)
public class SpringcoreApplication {

    public static void main(String[] args) {

        new SpringApplicationBuilder().sources(SpringcoreApplication.class)
                .initializers((ApplicationContextInitializer<GenericApplicationContext>) applicationContext ->{
                    //applicationContext.registerBean(OutSideDog.class); //패키지 바깥쪽에 있는 class들은 스프링 시작시점에 컨테이너에 등록
                }).run(args);

        //SpringApplication.run(SpringcoreApplication.class, args);
    }



    //필드, 수정자, 생성자)주입방법
    //lazy 주입법

}

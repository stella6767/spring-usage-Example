package com.example.beans.config;

import com.example.beans.dto.Dog;
import com.example.beans.dto.Horse;
import com.example.beans.dto.Turtle;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;


//@Configuration 이 붙은 객체를 가장 먼저 컨테이너에 등록을 시켜요. 그래서 이게 제일 먼저 로드되요. 설정에 필요한 작업들을 여기서 구현하면 되요.
//web.xml servlet.xml 같은 역할을 할 수 있다고 보면 돼요. 어플리케이션이 최초 로드되면서 가장 먼저 실행해야 될 작업들은 여기서 구현을 하시면 돼요.
@Configuration //얘도 빈으로 등록이 돼요. //저희가 이렇게 어노테이션을 붙인 것만으로도 bean으로 등록을 할 수 있어요.
@Slf4j
public class MyConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyConfig.class);


    public MyConfig() {
        LOGGER.info(this.toString() + " 등록!");

    }

//    @Bean //메서드 단위에서 이 어노테이션을 붙이면 돼요.
//    public Bird myBird(){
//
//        return new Bird();
//    }
//
//    @Bean //메서드 단위에서 이 어노테이션을 붙이면 돼요.
//    public Bird yourBird(){
//
//        Bird bird = new Bird();
//
//        log.info("yourBird " + bird.toString());
//
//        return bird;
//    }


//    @Bean(name = "yourdog")
//    public Dog dog(){
//
//        Dog dog = new Dog();
//
//        dog.name = "얼룩이";
//        log.info("yourdog =>{}", dog.toString());
//
//        return dog;
//    }




    @Scope("singleton") //즉 이 인스턴스는 어플리케이션이 구동되면서 뜨고, 어플리케이션이 소멸될 때까지 생명주기를 같이 한다는 거에요.
    @Bean(name = "mydog", initMethod = "init", destroyMethod = "close") //메서드 이름을 자유롭게 지을 수 있음. 스프링 코드에 의존하지않음
    public Dog dog2(){ //read-only 인 코드들, 외부 라이브러리 그런 라이브러리 클래스들의 초기화 종료 메서드를 호출할 수 있어요.

        Dog dog = new Dog();
        dog.name = "점박이";
        log.info("mydog =>{}", dog.toString());

        return dog;
    }


    @Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
    @Bean
    public Horse horse(){

        //싱글톤 방식으로 빈을 등록할 때 주의점이 있어요.
        //싱글톤으로 빈들을 등록하는 걸 남발하는 것.

        //이게 엄청난 장점이거든요. 그래서 이 편의에 맛들리는거에요. 그래서 굳이 등록할 필요없는 클래스들, 전부 다 bean으로 등록을 하는 거거든요.
        //이 싱글톤으로 등록할 때 주의해야할 점이 굳이 thread safe 한 객체를 등록해야돼요.
        log.info("1");
        return new Horse();
    }


//    @Bean
//    public Turtle turtle(){
//        log.info("2"); //2  가 두번 찍혀야 되죠. 어 스프링 어플리케이션 내부에서
//        return new Turtle();
//    }

    //지금 제가 말한 빈의 생명주기는 이제, 싱글톤 기준,


//    @Bean
//    public ObjectMapper objectMapper(){
//        return new ObjectMapper();
//    }


    //대충 Bean을 등록시키는 방법이 요런 것들이 있다. 정도만 알면돼요.
    //그러면 이제 이 bean들을 dI 시키는 다양한 방법들에 대해서 알아볼게요.


}

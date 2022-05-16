//package com.example.beans.dto2;
//
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Primary;
//import org.springframework.stereotype.Component;
//
//@Component  //이름 기본전략은싱글톤. 컨테이너는 같은 이름의 빈들을 중복해서 등록하지 못해요.
//public class Dog {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(Dog.class);
//
////    @Autowired
////    private Cat cat; //이 bean을 찾을 수 없음.
//
//    public Dog() {
//        LOGGER.info(this.toString() + " 등록!");
//    }
//
//    //private String name;
//
//    public void bark(){
//        LOGGER.info("왈왈");
//    }
//
//
//    //class를 하나 만들었죠. 여기서 ioc 개념에 대해서 진짜 초간단하게 알아볼게요.
//    //제어의 역전을 뜻하는 거죠.
//    //이제는 bean들을 등록하는 다양한 방법들에 대해서 알아볼게요.
//
//}

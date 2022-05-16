package com.example.beans;

import com.example.beans.dto.Dog;
import com.example.beans.service.BeanTestService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BeansApplicationTests {

    @Autowired //di 쉽게 말해서, 스프링 ioc 컨테이너에 등록된 bean들을 사용할 수 있게 주입시키는 거에요.
    private BeanTestService beanTestService;

    @Autowired
    DefaultListableBeanFactory df;


    @Test
    void contextLoads() {

    }


    @Test
    public void barkTest(){

        //저희가 직접 인스턴스를 만들고, 메서드를 호출했다면,
        //이번에 스프링 프레임워크에게 그 주도권을 내어준거에요.

        //beanTestService.diTest();

        //beanTestService.test();
        beanTestService.diTest();
        beanTestService.diTest();
        //초간단하게 말해서 ioc, di 의 개념이에요.


        //저희가 기대한 거는 매번 다른 인스턴스를 반환하는 걸 기대를 했는데.
    }


    @Test
    @DisplayName("모든 빈 출력하기")
    public void findAllBeans(){

        String[] beanDefinitionNames = df.getBeanDefinitionNames();

        for (String name : beanDefinitionNames) {

            Object bean = df.getBean(name);
            System.out.println("name = " + name + " object = " + bean);
        }

    }





}

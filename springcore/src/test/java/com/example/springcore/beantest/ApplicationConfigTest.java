package com.example.springcore.beantest;

import com.example.springcore.SpringcoreApplication;
import com.example.springcore.config.MyConfig;
import com.example.springcore.service.BeanTestService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationConfigTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(MyConfig.class);



    @Autowired
    DefaultListableBeanFactory df;


    @Autowired
    BeanTestService beanTestService;

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean(){

        String[] beanDefinitionNames = df.getBeanDefinitionNames();
        for (String beanName : beanDefinitionNames){
            Object bean = df.getBean(beanName);
            System.out.println("name = " + beanName + " object =" + bean);
        }
    }


    @Test
    void ditest(){
        //beanTestService.test();
        //beanTestService.asyncTest();
        beanTestService.기본빈테스트();
        beanTestService.기본빈테스트();
    }

    @Test
    @DisplayName("myconfig에 등록된 애플리케이션 빈 출력하기")
    public void findApplicationBeans(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanName : beanDefinitionNames){

            BeanDefinition beanDefinition = ac.getBeanDefinition(beanName);

            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                Object bean = ac.getBean(beanName);
                System.out.println("name = " + beanName + " object =" + bean);
            }

        }
    }








}

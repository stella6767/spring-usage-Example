package com.example.springcore.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class MyBeanPostProcessor implements BeanPostProcessor {


    //spring Bean이 초기화되기 전이나 후에 사용자정의 작업을 실행할 수 있고, 수정된 빈을 반환할
    //수도 있음.

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

      //log.info("bean initlaize 전 " + beanName);

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        //log.info("bean 초기화 후 " + beanName);

        return bean;
    }
}

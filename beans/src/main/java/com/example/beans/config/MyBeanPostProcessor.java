package com.example.beans.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;


//spring bean이 초기화되지 전이하 후에 여기서 사용자정의 작업을 실행할 수 있고, 수정된 빈을 반환할 수 도 있어요.

@Slf4j
@Configuration
public class MyBeanPostProcessor implements BeanPostProcessor {


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        //log.info("bean initlaize 전 " + beanName);

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        //log.info("bean initlalization 후 " + beanName);

        return bean;
    }
}

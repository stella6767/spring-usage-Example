package com.example.beans.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.Configuration;

//@Configuration
@Slf4j
public class CustomBeanNaming implements BeanNameGenerator {


    private final AnnotationBeanNameGenerator nameGenerator = new AnnotationBeanNameGenerator();

    @Override
    public String generateBeanName(BeanDefinition beanDefinition, BeanDefinitionRegistry beanDefinitionRegistry) {

        final String beanName;

        beanName = generateFullBeanName((AnnotatedBeanDefinition) beanDefinition);

        return beanName;
    }


    private String generateFullBeanName(final AnnotatedBeanDefinition definition){

        //패키지를 포함한 전체 이름을 반환
        return definition.getMetadata().getClassName();
    }



}

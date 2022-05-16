package com.example.springcore.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
@Slf4j
public class CustomBeanNaming implements BeanNameGenerator {


    private final AnnotationBeanNameGenerator defaultGenerator = new AnnotationBeanNameGenerator();

    private List<String> basePackages = new ArrayList<>();



    @Override
    public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {

        final String beanName;


        //full 패키지명으로 이름
        beanName = generateFullBeanName((AnnotatedBeanDefinition) definition);


        log.info("Registered Bean Name : " + beanName);

        return beanName;
    }



    private String generateFullBeanName(final AnnotatedBeanDefinition definition) {
        // 패키지를 포함한 전체 이름을 반환
        return definition.getMetadata().getClassName();
    }



//
//    private boolean isService(final BeanDefinition definition) {
//
//        if (definition instanceof AnnotatedBeanDefinition) {
//            final Set<String> annotationTypes = ((AnnotatedBeanDefinition) definition).getMetadata()
//                    .getAnnotationTypes();
//
//            return annotationTypes.stream()
//                    .filter(type -> type.equals(Service.class.getName()))
//                    .findAny()
//                    .isPresent();
//        }
//        return false;
//    }


}
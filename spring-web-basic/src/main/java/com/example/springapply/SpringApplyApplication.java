package com.example.springapply;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;


@ServletComponentScan
@SpringBootApplication
public class SpringApplyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringApplyApplication.class, args);
    }

}

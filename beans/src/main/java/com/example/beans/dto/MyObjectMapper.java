package com.example.beans.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class MyObjectMapper extends ObjectMapper {

    //Ftp

    public MyObjectMapper() {
        System.out.println("??????");
    }
}

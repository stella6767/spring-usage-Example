package com.example.jpademo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class MyMemberDto {

    private String name;
    private Integer age;

    public MyMemberDto(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}

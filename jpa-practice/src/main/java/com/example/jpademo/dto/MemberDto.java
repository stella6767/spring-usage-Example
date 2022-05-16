package com.example.jpademo.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@Data
public class MemberDto {

    private String username;
    private Integer age;

    @QueryProjection //dto도 q 타입을 생성
    public MemberDto(String username, Integer age) {
        this.username = username;
        this.age = age;
    }
}

package com.example.jpademo.domain.member;

//OSIV의 문제점을 해결하기 위해,
public class MemberWrapper {

    private Member member;

    public MemberWrapper(Member member) {
        this.member = member;
    }

    public String getName() {
       return member.getUsername(); //읽기 전용 메서드만 생성
    }
}

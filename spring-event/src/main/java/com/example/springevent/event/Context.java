package com.example.springevent.event;


import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * ThreadLocal 객체를 생성한다.
 * ThreadLocal.set() 메서드를 이용해서 현재 쓰레드의 로컬 변수에 값을 저장한다.
 * ThreadLocal.get() 메서드를 이용해서 현재 쓰레드의 로컬 변수 값을 읽어온다.
 * ThreadLocal.remove() 메서드를 이용해서 현재 쓰레드의 로컬 변수 값을 삭제한다.
 */



public class Context {

    public static ThreadLocal<User> local = new ThreadLocal<>();


}


@Component
class A {

    @PostConstruct
    public void a() {

        System.out.println("a 시작");
        Context.local.set(new User(1L, "kang"));

        B b = new B();
        b.b();

        Context.local.remove();
    }
}

class B {
    public void b() {


        User user = Context.local.get();
        System.out.println("b 시작" + user.toString());

        C c = new C();
        c.c();
    }
}

class C {
    public void c() {
        User user = Context.local.get();
        System.out.println("c 시작" + user.toString());
    }
}

/**
 *  A.a()에서 생성한 Date 객체를 B.b() 메서드나 C.c() 메서드에 파라미터로 전달하지 않는다는 것이다. 즉, 파라미터로 객체를 전달하지 않아도 한 쓰레드로 실행되는 코드가 동일한 객체를 참조할 수 있게 된다.
 *  ThreadLocal은 한 쓰레드에서 실행되는 코드가 동일한 객체를 사용할 수 있도록 해 주기 때문에 쓰레드와 관련된 코드에서 파라미터를 사용하지 않고 객체를 전파하기 위한 용도로 주로 사용되며, 주요 용도는 다음과 같다.
 *
 * 사용자 인증정보 전파 - Spring Security에서는 ThreadLocal을 이용해서 사용자 인증 정보를 전파한다.
 * 트랜잭션 컨텍스트 전파 - 트랜잭션 매니저는 트랜잭션 컨텍스트를 전파하는 데 ThreadLocal을 사용한다.
 * 쓰레드에 안전해야 하는 데이터 보관
 *
 * ThreadLocal 변수에 보관된 데이터의 사용이 끝나면 반드시 해당 데이터를 삭제해 주어야 한다. 그렇지 않을 경우 재사용되는 쓰레드가 올바르지 않은 데이터를 참조할 수 있다.
 */
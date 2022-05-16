package com.example.springevent.event;

/**
 * 이벤트 기반 프로그래밍 할 때 유용하게 쓸 수 있겠다.
 * 약간 안드로이드의 이벤트버스 느낌도 나고
 */


public class MyEvent {

    private int data;

    private Object source;

    public MyEvent(int data, Object source) {
        this.data = data;
        this.source = source;
    }

    public int getData() {
        return data;
    }

    public Object getSource() {
        return source;
    }
}
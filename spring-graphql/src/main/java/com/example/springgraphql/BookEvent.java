package com.example.springgraphql;

import lombok.Data;

@Data
public class BookEvent {

    private Book book;
    private BookEventType bookEvent;


}

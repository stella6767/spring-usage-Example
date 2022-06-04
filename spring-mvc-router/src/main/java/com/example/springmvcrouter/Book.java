package com.example.springmvcrouter;


import lombok.Data;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Data
public class Book {

    private long id;
    private String name;
    private int price;

    public Book(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public Book() {



        this.price = ThreadLocalRandom.current().nextInt(100, 1000);

        this.name = new Random().ints(48,122 + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(10)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

    }
}

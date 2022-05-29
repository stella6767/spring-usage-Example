package com.example.springgraphql;


import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class BookInput {
    private String name;
    private int price;

    public Book toEntity(){

        return new Book(this.name, this.price);
    }

}

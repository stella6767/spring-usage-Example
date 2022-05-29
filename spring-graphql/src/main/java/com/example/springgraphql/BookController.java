package com.example.springgraphql;


import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;


@Slf4j
@Controller
public class BookController {

    /**
     * https://www.youtube.com/watch?v=kVSYVhmvNCI&t=2211s&ab_channel=SpringDeveloper
     * https://www.youtube.com/watch?v=atA2OovQBic&t=899s&ab_channel=SpringAcademy
     */


    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     *
     * query {
     *     greeting
     *     findAll {
     *         id
     *         name
     *     }
     *     findById(id: 98) {
     *         name
     *     }
     * }
     *
     * @return
     */


    @QueryMapping(name = "greeting")
    public String greeting() {

        return "hello spring-graphql!!!!!!";
    }



    @SchemaMapping(typeName = "Query", field = "findAll")
    public List<Book> findAll(){

        return bookService.findAll();
    }


    @QueryMapping
    public Book findById(@Argument long id){

        log.info("id: " + id);

        return bookService.findById(id);
    }


    /**
     * mutation {
     *   addBook(book: {name: "222", price: 111}) {
     *     id
     *     name
     *     price
     *   }
     * }
     * @param bookInput
     * @return
     */

    @MutationMapping
    public Book addBook(@Argument(name = "book") BookInput bookInput){

        log.info("book input >>>" + bookInput);

        return bookService.save(bookInput.toEntity());
    }


    @SubscriptionMapping
    public BookEvent bookEvents(@Argument long id){

        //return this.bookService.findById(id);

        return null;
    }

}

package com.example.springmvcrouter;
import static org.springframework.web.servlet.function.RouterFunctions.route;
import static org.springframework.web.servlet.function.ServerResponse.*;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.function.*;

import java.util.List;


@Slf4j
@Component
public class BookController {


    private final BookService bs;

    public BookController(BookService bookService) {
        this.bs = bookService;
    }


    public RouterFunction<ServerResponse> bookListing() {
        return route().GET("/book", req -> ok().body(bs.findAll()))
                .build();
    }

    public RouterFunction<ServerResponse> bookSearch() {
        return route().nest(RequestPredicates.path("/book"), builder -> {
                    builder.GET("/name/{name}", req -> ok().body(bs.findByName(req.pathVariable("name"))))
                            .GET("/id/{id}", req -> ok().body(bs.findById(Integer.parseInt(req.pathVariable("id")))));
                })
                .onError(IllegalArgumentException.class, (e, req) -> EntityResponse.fromObject(new Error(e.getMessage()))
                        .status(HttpStatus.NOT_FOUND)
                        .build())
                .build();
    }

    public RouterFunction<ServerResponse> adminFunctions() {
        return route().POST("/book", req -> ok().body(bs.save(req.body(Book.class))))
                .filter((req, next) -> authenticate(req) ? next.handle(req) : status(HttpStatus.UNAUTHORIZED).build())
                .onError(IllegalArgumentException.class, (e, req) -> EntityResponse.fromObject(new Error(e.getMessage()))
                        .status(HttpStatus.BAD_REQUEST)
                        .build())
                .build();
    }

    public RouterFunction<ServerResponse> remainingProductRoutes() {
        return route().add(bookSearch())
                .add(adminFunctions())
                .build();
    }

    private boolean authenticate(ServerRequest req) {
        return Boolean.TRUE;
    }


}

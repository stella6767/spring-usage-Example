package com.example.springgraphql;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class SpringGraphqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringGraphqlApplication.class, args);
    }


    @Bean
    public ApplicationRunner myCLRunner(BookRepository bookRepository){

        return args -> {
            System.out.println("init dummy books");

            List<Book> books = Arrays.asList(new Book(), new Book(), new Book(), new Book(), new Book());

            for (Book book : books) {
                bookRepository.save(book);
            }


        };
    }

}

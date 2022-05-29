package com.example.springgraphql;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;


@Slf4j
@Repository
public class BookRepository {


    private static ConcurrentHashMap<Long, Book> store = new ConcurrentHashMap<>();


    public Book save(Book book) {

        book.setId( ThreadLocalRandom.current().nextLong(1L, 100L));
        log.info("book save: " + book);
        store.put(book.getId(), book);

        return book;
    }


    public List<Book> findAll() {
        ArrayList<Book> books = new ArrayList<>();

        for (Long i :store.keySet()) {
            books.add(store.get(i));
        }
        return books;
    }

    public Optional<Book> findById(long id) {
        return Optional.ofNullable(store.get(id));
    }

}

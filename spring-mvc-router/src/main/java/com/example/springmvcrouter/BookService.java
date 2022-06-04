package com.example.springmvcrouter;


import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findById(long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("not found book"));
    }


    public Book save(Book book) {

        return bookRepository.save(book);
    }

    public Object findByName(String name) {

        return null;
    }
}

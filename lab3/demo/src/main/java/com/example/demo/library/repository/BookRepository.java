package com.example.demo.library.repository;

import com.example.demo.library.model.Book;
import java.util.Collection;
import java.util.Optional;

public interface BookRepository {

    Book save(Book book);

    Optional<Book> findById(String id);

    Collection<Book> findAll();
}

package com.glqdlt.ex.tddspringwebapp;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * @author glqdlt
 * @since 0.0.1
 */
@Service
public class BookServiceImpl implements BookService {

    @Override
    public List<Book> findAllBooks() {
        return new LinkedList<>();
    }

    @Override
    public Book saveNewBook(Book book) {
        return book;
    }
}

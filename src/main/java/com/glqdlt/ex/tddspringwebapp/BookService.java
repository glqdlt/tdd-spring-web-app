package com.glqdlt.ex.tddspringwebapp;


import java.util.List;

/**
 * @author glqdlt
 * @since 0.0.1
 */
public interface BookService {
    List<Book> findAllBooks();
    Book saveNewBook(Book book);
}

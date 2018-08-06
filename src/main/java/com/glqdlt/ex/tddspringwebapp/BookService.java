package com.glqdlt.ex.tddspringwebapp;


import java.util.List;

public interface BookService {

    List<Book> findAllBooks();
    Book saveNewBook(Book book);
}

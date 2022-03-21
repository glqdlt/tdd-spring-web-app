package com.glqdlt.ex.tddspringwebapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author glqdlt
 */
@Service
public class SimpleDomainService {

    @Autowired
    private BookRepository bookRepository;

    public String getBookTitle(Integer bookId){
        BookEntity book = bookRepository.findOne(bookId);
        return messageDecorate(book);
    }
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public String titlePrefix(){
        return LocalDateTime.now().format(FORMAT);
    }

    public String messageDecorate(Book b){
        return String.format("[%s]%s", titlePrefix(),b.getTitle());
    }

}

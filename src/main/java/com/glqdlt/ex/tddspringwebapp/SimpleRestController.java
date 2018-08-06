package com.glqdlt.ex.tddspringwebapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class SimpleRestController {

    @Autowired
    BookService bookService;

    @PostMapping("/book")
    public ResponseEntity saveNewBook(Book book){
        Book bewBook = bookService.saveNewBook(book);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}

package com.glqdlt.ex.tddspringwebapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
public class SimpleRestController {

    @Autowired
    BookService bookService;

    @PostMapping("/book")
    public ResponseEntity<Book> saveNewBook(@RequestParam Book book) {
        Book newBook = bookService.saveNewBook(book);
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }

}

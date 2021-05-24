package com.glqdlt.ex.tddspringwebapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author glqdlt
 * @since 0.0.1
 */
@RequestMapping("/api")
@RestController
public class SimpleRestController {

    @Autowired
    private BookService bookService;

    @GetMapping("/book")
    public ResponseEntity<List<Book>> findAllBook() {
        return ResponseEntity.ok()
                .body(bookService.findAllBooks());
    }

    @PostMapping("/book")
    public ResponseEntity<Book> createNewBook(String title,
                                              String author) {
        Book book = new Book(title, author);

        Book newBook = bookService.saveNewBook(book);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(newBook);
    }

}

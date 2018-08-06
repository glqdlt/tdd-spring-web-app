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

    @PostMapping("/save")
    public ResponseEntity saveNewBook(){
        Book book = bookService.saveNewBook();
        return new ResponseEntity<>(book,HttpStatus.CREATED);
    }

}

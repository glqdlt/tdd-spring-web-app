package com.glqdlt.ex.tddspringwebapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class SimpleRestController {

    @Autowired
    BookService bookService;

}

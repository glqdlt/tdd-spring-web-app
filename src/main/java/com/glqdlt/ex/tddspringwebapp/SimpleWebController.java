package com.glqdlt.ex.tddspringwebapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@Controller
public class SimpleWebController {


    @Autowired
    BookService bookService;

}

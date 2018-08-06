package com.glqdlt.ex.tddspringwebapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/")
@Controller
public class SimpleWebController {


    @Autowired
    BookService bookService;

    @GetMapping("books")
    public ModelAndView books(ModelAndView modelAndView) {

        modelAndView.setViewName("book-dashboard");
        modelAndView.addObject("books", bookService.findAllBooks());
        return modelAndView;
    }

}

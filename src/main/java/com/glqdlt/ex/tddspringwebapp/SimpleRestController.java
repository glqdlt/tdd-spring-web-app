package com.glqdlt.ex.tddspringwebapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.HashMap;
import java.util.List;

/**
 * @author glqdlt
 * @since 0.0.1
 */
@RequestMapping("/api")
@Validated
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
    public ResponseEntity<Book> createNewBook(@Valid @Pattern(regexp = "[0-9a-zA-Z가-힣]{1,100}", message = "제목은 영문한글숫자 100글자 미만만 됩니다.") String title,
                                              @Valid @Pattern(regexp = "[0-9a-zA-Z가-힣]{1,20}", message = "작가 이름은 20자리 미만, 영문한글만됩니다.") String author) {
        Book book = new Book(title, author);

        Book newBook = bookService.saveNewBook(book);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(newBook);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> resolveValid(ConstraintViolationException e) {
        return ResponseEntity.badRequest()
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(e.getConstraintViolations().stream()
                        .map(ConstraintViolation::getMessage)
                        .reduce((a, x) -> a + "," + x)
                        .map(x -> {
                            HashMap<String, Object> r = new HashMap<>();
                            r.put("message", x);
                            r.put("code", 400);
                            return r;
                        })
                        .orElse(new HashMap<>())
                );
    }
}

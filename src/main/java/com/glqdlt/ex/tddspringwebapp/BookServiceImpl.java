package com.glqdlt.ex.tddspringwebapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author glqdlt
 * @since 0.0.1
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(x -> (Book) x)
                .collect(Collectors.toList());
    }

    @Override
    public Book saveNewBook(Book book) {
        return bookRepository.save(BookEntity.valueOf(book));
    }
}

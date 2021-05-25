package com.glqdlt.ex.tddspringwebapp.service;

import com.glqdlt.ex.tddspringwebapp.*;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.Condition;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService = new BookServiceImpl();

    @Test
    public void testBookServiceFindAll() {
        BookEntity stub = new BookEntity();
        stub.setId(1);
        stub.setTitle("title");
        stub.setAuthor("author");
        BDDMockito.given(bookRepository.findAll())
                .willReturn(Collections.singletonList(stub));

        List<Book> result = bookService.findAllBooks();

        Assertions.assertThat(result)
                .size()
                .is(new Condition<Integer>() {
                    @Override
                    public boolean matches(Integer integer) {
                        return integer.equals(1);
                    }
                }).returnToIterable()
                .first()
                .is(new Condition<Book>() {
                    @Override
                    public boolean matches(Book book) {
                        return book instanceof BookEntity &&
                                book.getTitle().equals(stub.getTitle())
                                && book.getAuthor().equals(stub.getAuthor());
                    }
                });

    }

    @Test
    public void testBookServiceSaveNewBook() {
        Book book
                = new Book();
        book.setAuthor("author");
        book.setTitle("title");

        bookService.saveNewBook(book);

//        **Mockito style**
//        BDDMockito.verify(bookRepository)
//                .save(BookEntity.valueOf(book));
//        **BddMockito Style**
        BDDMockito.then(bookRepository)
                .should()
                .save(BookEntity.valueOf(book));
    }
}
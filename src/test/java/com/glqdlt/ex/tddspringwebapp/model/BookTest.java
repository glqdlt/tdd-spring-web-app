package com.glqdlt.ex.tddspringwebapp.model;

import com.glqdlt.ex.tddspringwebapp.Book;
import com.glqdlt.ex.tddspringwebapp.BookEntity;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author glqdlt
 * @since 0.0.1
 */
public class BookTest {
    @Test
    public void isNotNullBook() {
        Book book = Book.builder()
                .title("어른왕자")
                .author("jhun")
                .build();
        Assert.assertNotNull(book);
        Assert.assertEquals("어른왕자", book.getTitle());
        Assert.assertEquals("jhun", book.getAuthor());
    }


    @Test
    public void canCreateBookByAllArgs() {
        Book someBook = new Book("제목", "작가");
        Assert.assertEquals("제목", someBook.getTitle());
        Assert.assertEquals("작가", someBook.getAuthor());
    }

    @Test
    public void testStaticValueOfMethod() {
        Book book
                = new Book();
        book.setTitle("title");
        book.setAuthor("author");

        BookEntity result = BookEntity.valueOf(book);
        Assert.assertEquals(book.getTitle(), result.getTitle());
        Assert.assertEquals(book.getAuthor(), result.getAuthor());
    }
}
package com.glqdlt.ex.tddspringwebapp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BookTest {

    private Book book;

    @Before
    public void setUp() throws Exception {
        this.book = Book.builder()
                .title("어른왕자")
                .author("jhun")
                .build();
    }

    @Test
    public void isNotNullBook() {
        Assert.assertNotNull(book);
        Assert.assertEquals("어른왕자", book.getTitle());
        Assert.assertEquals("jhun", book.getAuthor());
    }
}
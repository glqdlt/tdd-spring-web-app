package com.glqdlt.ex.tddspringwebapp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@WebMvcTest(value = SimpleWebController.class)
@RunWith(SpringRunner.class)
@Slf4j
public class SimpleWebControllerTest {


    @Autowired
    MockMvc mockMvc;

    @MockBean
    BookService bookService;

    private List<Book> books;

    @Before
    public void setUp() {

        books = IntStream.rangeClosed(1, 10).boxed().map(x -> new Book("title" + x, "author")).collect(Collectors.toList());

    }

    @Test
    public void shouldViewBookDashboardAllBooks() throws Exception {
        Mockito.when(bookService.findAllBooks()).thenReturn(books);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/books");

        MvcResult result = mockMvc.perform(request).andReturn();
        List<Book> resultBooks = (List<Book>) result.getModelAndView().getModel().get("books");

        Assert.assertEquals(10, resultBooks.size());
        Assert.assertEquals("title1", resultBooks.get(0).getTitle());
    }
}
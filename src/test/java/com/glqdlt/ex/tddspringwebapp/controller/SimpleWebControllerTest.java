package com.glqdlt.ex.tddspringwebapp.controller;

import com.glqdlt.ex.tddspringwebapp.Book;
import com.glqdlt.ex.tddspringwebapp.BookService;
import com.glqdlt.ex.tddspringwebapp.SimpleWebController;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author glqdlt
 * @since 0.0.1
 */
@WebMvcTest(value = SimpleWebController.class)
@RunWith(SpringRunner.class)
@Slf4j
public class SimpleWebControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    private List<Book> books;

    @Before
    public void setUp() {

        books = IntStream.rangeClosed(1, 10).boxed().map(x -> new Book("title" + x, "author")).collect(Collectors.toList());

    }

    @SuppressWarnings("unchecked")
    @Test
    public void shouldViewBookDashboardAllBooksLegacy() throws Exception {

//GIVEN
        Mockito.when(bookService.findAllBooks()).thenReturn(books);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/view/book");
        MvcResult result = mockMvc.perform(request).andReturn();
        List<Book> resultBooks = (List<Book>) result.getModelAndView().getModel().get("books");
        Assert.assertEquals(10, resultBooks.size());
        Assert.assertEquals("title1", resultBooks.get(0).getTitle());
    }

    @Test
    public void shouldViewBookDashboardAllBooksModernStyle() throws Exception {

        Mockito.when(bookService.findAllBooks()).thenReturn(books);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/view/book");

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.view().name("book-dashboard"))
                .andExpect(MockMvcResultMatchers.model().attribute("books", books));

//        BookService#findAllBooks() 실제 호출 여부
        Mockito.verify(bookService).findAllBooks();
//        추가 호출 됬는지
        Mockito.verifyNoMoreInteractions(bookService);
    }
}
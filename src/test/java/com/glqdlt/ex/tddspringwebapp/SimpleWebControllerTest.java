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

    @Test
    public void shouldViewBookDashboardAllBooksLegacy() throws Exception {

//GIVEN
        Mockito.when(bookService.findAllBooks()).thenReturn(books);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/view/book");

//        리퀘스트를 수행하고, 반환되는 것이 있을 것이다.
        MvcResult result = mockMvc.perform(request).andReturn();

//        books 라는 모델의 값을 가져와서 resultBooks에 담는다.
        List<Book> resultBooks = (List<Book>) result.getModelAndView().getModel().get("books");

//        실제 값과 expected 값을 비교
        Assert.assertEquals(10, resultBooks.size());
        Assert.assertEquals("title1", resultBooks.get(0).getTitle());
    }

    @Test
    public void shouldViewBookDashboardAllBooksModernStyle() throws Exception {

//      findAllBooks가 호출될 때에 books를 반환하도록 해놓는다.
        Mockito.when(bookService.findAllBooks()).thenReturn(books);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/view/book");

        mockMvc.perform(request)
//        request 수행 중에 book-dashboard.html 을 return 하는 지..
                .andExpect(MockMvcResultMatchers.view().name("book-dashboard"))
//        reqeust 수행 중에 model 에서 가져온 데이터가 books와 같은 지..
                .andExpect(MockMvcResultMatchers.model().attribute("books", books));

//        findAllBooks 가 컨트롤러를 통해 실제로 호출 되었을 때를 검증한다.
        Mockito.verify(bookService).findAllBooks();
//        bookService 와 더 이상의 호출이 없는지
        Mockito.verifyNoMoreInteractions(bookService);
    }
}
package com.glqdlt.ex.tddspringwebapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.nashorn.internal.runtime.options.LoggingOption;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.GsonTester;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = SimpleRestController.class)
@RunWith(SpringRunner.class)
@Slf4j
public class SimpleRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    BookService bookService;

    private Book book;

    @Before
    public void setUp() throws Exception {
        book = Book.builder().title("호호").author("하하").build();
    }

    @Test
    public void createNewBook() throws Exception {

//BDD Style (http://www.baeldung.com/bdd-mockito)
//https://memorynotfound.com/unit-test-spring-mvc-rest-service-junit-mockito/

        BDDMockito.when(bookService.saveNewBook(book))
                .thenReturn(book);



        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(book);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/api/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
//                왜 여기서 null 값으로 나올까?
                .andExpect(MockMvcResultMatchers.content().json(json));

        BDDMockito.verify(bookService).saveNewBook(book);
        BDDMockito.verifyNoMoreInteractions(bookService);




    }
}

package com.glqdlt.ex.tddspringwebapp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.List;

/**
 * @author glqdlt
 * @since 0.0.1
 */
@WebMvcTest(value = SimpleRestController.class)
@RunWith(SpringRunner.class)
@Slf4j
public class SimpleRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    public void testFindAllBook() throws Exception {
        List<Book> stub = Collections.singletonList(new Book("title", "author"));
        BDDMockito.given(bookService.findAllBooks())
                .willReturn(stub);

        MockHttpServletRequestBuilder req = MockMvcRequestBuilders.get("/api/book")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE);

        mockMvc.perform(req)
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().json("[{\"title\":\"title\",\"author\":\"author\"}]"));
    }

    @Test
    public void testCreateNewBook() throws Exception {

//GIVEN
        BDDMockito.given(bookService.saveNewBook(BDDMockito.any()))
                .willReturn(Book.builder()
                        .title("호호")
                        .author("하하")
                        .build());
//WHEN
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/api/book")
                .content("title=하하&author=호호")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE);
//THEN
        mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.content().json("{\"title\":\"호호\",\"author\":\"하하\"}"));

    }


    @Test
    public void testBadCreateNewBook() throws Exception {

//GIVEN
        BDDMockito.given(bookService.saveNewBook(BDDMockito.any()))
                .willReturn(Book.builder()
                        .title("호호")
                        .author("하하")
                        .build());
//WHEN
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/api/book")
                .content("title=하하!!&author=호호")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE);
//THEN
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.content().json("{\"code\":400,\"message\":\"제목은 영문한글숫자 100글자 미만만 됩니다.\"}"));

    }
}

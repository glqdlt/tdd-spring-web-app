package com.glqdlt.ex.tddspringwebapp;

import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

@WebMvcTest(value = SimpleRestController.class)
@RunWith(SpringRunner.class)
@Slf4j
public class SimpleRestControllerTest {

}
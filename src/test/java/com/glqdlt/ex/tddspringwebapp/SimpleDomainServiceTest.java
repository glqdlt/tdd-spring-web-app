package com.glqdlt.ex.tddspringwebapp;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SimpleDomainServiceTest {

    @InjectMocks @Spy
    SimpleDomainService simpleDomainService;

    @Mock
    BookRepository bookRepository;

    @Test
    public void canMessageDecorate() {

        BookEntity dummy = new BookEntity();
        dummy.setTitle("cleancode");

        Mockito.when(bookRepository.findOne(Mockito.anyInt()))
                .thenReturn(dummy);

        Mockito.when(simpleDomainService.titlePrefix())
                .thenReturn("2022-01-01");

        Assert.assertEquals("[2022-01-01]cleancode", simpleDomainService.getBookTitle(1));

    }
}
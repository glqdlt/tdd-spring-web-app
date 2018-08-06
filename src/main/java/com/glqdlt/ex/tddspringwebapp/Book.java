package com.glqdlt.ex.tddspringwebapp;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;


@ToString
@Getter
@Builder
public class Book {

    private String title;
    private String author;

}

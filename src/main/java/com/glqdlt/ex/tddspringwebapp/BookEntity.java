package com.glqdlt.ex.tddspringwebapp;

import javax.persistence.*;

/**
 * @author glqdlt
 * @since 0.0.1
 */
@Entity
@Table(name = "book")
public class BookEntity extends Book {
    private Integer id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getTitle() {
        return super.getTitle();
    }

    @Override
    public String getAuthor() {
        return super.getAuthor();
    }
}

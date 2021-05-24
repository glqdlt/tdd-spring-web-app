package com.glqdlt.ex.tddspringwebapp;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author glqdlt
 * @since 0.0.1
 */
public interface BookRepository extends JpaRepository<BookEntity, Integer> {
}

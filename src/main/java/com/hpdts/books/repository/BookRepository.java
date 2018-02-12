package com.hpdts.books.repository;

import com.hpdts.books.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findByAuthor(String author);
    List<Book> findByEditor(String editor);

}

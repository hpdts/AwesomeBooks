package com.hpdts.books.service;


import com.hpdts.books.model.Book;

import java.util.List;

public interface IBookService {

    void getBooksAndSave(String title);

    List<Book> findBooksByAuthor(String author);

    List<Book> findBooksByEditor(String editor);
}

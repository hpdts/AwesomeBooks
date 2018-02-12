package com.hpdts.books.service;


import com.hpdts.books.model.Book;

import java.util.List;

public interface IBookAPI {

    List<Book> findBookByTitle(String title);
}

package com.hpdts.books.service;

import com.hpdts.books.controller.BookController;
import com.hpdts.books.model.Book;
import com.hpdts.books.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BookService implements IBookService{
    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private IBookAPI bookAPI;

    public void save(Book book) {
        bookRepository.save(book);
    }

    public void save(List<Book> books) {
        for(Book book : books){
            save(book);
        }
    }

    @Override
    public void getBooksAndSave(String title) {
        try {
            List<Book> books = bookAPI.findBookByTitle(title);

            if (books == null) {
                logger.warn("Given title " + title + " book does not exist");
            } else {
                save(books);
                logger.info("Books saved.");
            }
        }catch(Exception ex){
            logger.error("Error on getBooksAndSave", ex);
        }
    }

    @Override
    public List<Book> findBooksByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    @Override
    public List<Book> findBooksByEditor(String editor) {
        return bookRepository.findByEditor(editor);
    }
}

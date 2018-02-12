package com.hpdts.books.service;

import com.hpdts.books.model.Book;
import com.hpdts.books.model.GBookVolumeInfoWrapper;
import com.hpdts.books.model.GBookWrapper;
import com.hpdts.books.model.GoogleBookItemsWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BookAPI implements IBookAPI {

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<Book> findBookByTitle(String title) {

        //return getMockBook(title);

        return getAPIBooks(title);
    }

    private Book getMockBook(String title) {
        return new Book(1, title, "Pablo Neruda", "Chilena");
    }

    public List<Book> getAPIBooks(String title) {
        String bookAPIResource = "https://www.googleapis.com/books/v1/volumes?q=%s";

        List<Book> books = new ArrayList<>();
        String url = String.format(bookAPIResource, title);
        ResponseEntity<GBookWrapper> response = restTemplate.getForEntity(url, GBookWrapper.class);

        for(GoogleBookItemsWrapper item : response.getBody().getItems()) {
            GBookVolumeInfoWrapper volume = item.getVolumeInfo();
            Book book = new Book();
            book.setTitle(volume.getTitle());
            book.setEditor(volume.getPublisher());

            StringBuilder authors = new StringBuilder();
            if (volume.getAuthors() != null){
                Arrays.stream(volume.getAuthors()).forEach(authors::append);
                book.setAuthor(authors.toString());
            }
            books.add(book);
        }

        return books;
    }
}

package com.hpdts.books.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashMap;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown=true)
public class GBookVolumeInfoWrapper {

    private String title;

    private String publisher;
    private String publishedDate;
    private String[] authors;

    private String description;

    private int pageCount;
    private int printType;
    private String[] categories;
    private Map<String, String> imageLinks = new HashMap<>();
    private String language;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String[] getAuthors() {
        return authors;
    }

    public void setAuthors(String[] authors) {
        this.authors = authors;
    }



}
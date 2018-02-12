package com.hpdts.books.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class GBookWrapper {

    private int totalItems;
    private GoogleBookItemsWrapper[] items;


    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public GoogleBookItemsWrapper[] getItems() {
        return items;
    }

    public void setItems(GoogleBookItemsWrapper[] items) {
        this.items = items;
    }


}

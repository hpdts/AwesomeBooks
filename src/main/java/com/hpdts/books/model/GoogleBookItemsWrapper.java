package com.hpdts.books.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class GoogleBookItemsWrapper {

    private GBookVolumeInfoWrapper volumeInfo;

    public GBookVolumeInfoWrapper getVolumeInfo() {
        return volumeInfo;
    }

    public void setVolumeInfo(GBookVolumeInfoWrapper volumeInfo) {
        this.volumeInfo = volumeInfo;
    }


}

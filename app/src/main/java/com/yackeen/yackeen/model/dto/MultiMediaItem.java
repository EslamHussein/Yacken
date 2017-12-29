package com.yackeen.yackeen.model.dto;


import android.arch.persistence.room.Ignore;

/**
 * Created by EslamHussein on 12/28/17.
 */


public class MultiMediaItem {
    private String url;
    private int height;
    private int width;

    public MultiMediaItem() {
    }

    @Ignore
    public MultiMediaItem(String url, int height, int width) {
        this.url = url;
        this.height = height;
        this.width = width;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }


}
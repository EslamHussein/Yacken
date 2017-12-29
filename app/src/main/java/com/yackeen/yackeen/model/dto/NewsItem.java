package com.yackeen.yackeen.model.dto;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;
import com.yackeen.yackeen.model.db.DBConstant;


/**
 * Created by EslamHussein on 12/28/17.
 */
@Entity(tableName = DBConstant.NEWS_ITEM_TABLE_NAME)
public class NewsItem {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = DBConstant.NEWS_ITEM_id)
    private int id;

    @ColumnInfo(name = DBConstant.NEWS_ITEM_TITLE)
    private String title;

    @ColumnInfo(name = DBConstant.NEWS_ITEM_PUBLISHED_DATE)
    @SerializedName("published_date")
    private String publishedDate;

    @Embedded
    private MultiMediaItem multiMediaItem;

    public NewsItem() {

    }

    @Ignore
    public NewsItem(String title, String publishedDate, MultiMediaItem multimedia) {
        this.title = title;
        this.publishedDate = publishedDate;
        this.multiMediaItem = multimedia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public MultiMediaItem getMultiMediaItem() {
        return multiMediaItem;
    }

    public void setMultiMediaItem(MultiMediaItem multiMediaItem) {
        this.multiMediaItem = multiMediaItem;
    }
}
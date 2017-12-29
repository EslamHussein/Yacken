package com.yackeen.yackeen.model.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.yackeen.yackeen.model.db.DBConstant;
import com.yackeen.yackeen.model.dto.NewsItem;

import java.util.List;

/**
 * Created by EslamHussein on 12/29/17.
 */
@Dao
public interface NewsItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<NewsItem> items);

    @Query("SELECT * FROM " + DBConstant.NEWS_ITEM_TABLE_NAME)
    List<NewsItem> getAll();

    @Query("DELETE FROM " + DBConstant.NEWS_ITEM_TABLE_NAME)
    void deleteAll();
}

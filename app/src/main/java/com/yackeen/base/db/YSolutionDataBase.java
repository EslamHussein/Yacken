package com.yackeen.base.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.yackeen.yackeen.model.db.dao.NewsItemDao;
import com.yackeen.yackeen.model.db.dao.WeatherDao;
import com.yackeen.yackeen.model.dto.NewsItem;
import com.yackeen.yackeen.model.dto.WeatherItem;

/**
 * Created by EslamHussein on 12/28/17.
 */

@Database(entities = {WeatherItem.class, NewsItem.class}, version = 1, exportSchema = false)
public abstract class YSolutionDataBase extends RoomDatabase {

    public abstract WeatherDao weatherDao();

    public abstract NewsItemDao newsItemDao();


}

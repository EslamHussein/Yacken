package com.yackeen.yackeen.model.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.yackeen.yackeen.model.db.DBConstant;
import com.yackeen.yackeen.model.dto.WeatherItem;

import java.util.List;

/**
 * Created by EslamHussein on 12/28/17.
 */
@Dao
public interface WeatherDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<WeatherItem> weatherItems);

    @Query("SELECT * FROM " + DBConstant.WEATHER_TABLE_NAME)
    List<WeatherItem> getAll();

    @Query("DELETE FROM " + DBConstant.WEATHER_TABLE_NAME)
    void deleteAll();

}

package com.yackeen.yackeen.model.dto;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;
import com.yackeen.yackeen.model.db.DBConstant;


/**
 * Created by EslamHussein on 12/28/17.
 */

@Entity(tableName = DBConstant.WEATHER_TABLE_NAME)
public class WeatherItem {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = DBConstant.ITEM_ID)
    private int id;

    @ColumnInfo(name = DBConstant.ITEM_DT)
    private long dt;

    @SerializedName("dt_txt")
    @ColumnInfo(name = DBConstant.ITEM_DT_TEXT)
    private String dtTxt;
    @Embedded
    private MainTemp main;


    public WeatherItem() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public String getDtTxt() {
        return dtTxt;
    }

    public void setDtTxt(String dtTxt) {
        this.dtTxt = dtTxt;
    }

    public MainTemp getMain() {
        return main;
    }

    public void setMain(MainTemp main) {
        this.main = main;
    }
}

package com.yackeen.yackeen.model.db;

/**
 * Created by EslamHussein on 12/28/17.
 */

public interface DBConstant {


    String DB_NAME = "YSolution_db";
    String WEATHER_TABLE_NAME = "ITEM";
    // ITEM table fields
    String ITEM_ID = "id";
    String ITEM_DT = "dt";
    String ITEM_DT_TEXT = "dt_txt";
    String ITEM_MAIN_TEMP = "main_temp";


    String NEWS_ITEM_TABLE_NAME = "NEWS_ITEM";


    String NEWS_ITEM_id = "NEWS_ITEM_ID";
    String NEWS_ITEM_SECTION = "NEWS_ITEM_SECTION";
    String NEWS_ITEM_TITLE = "NEWS_ITEM_TITLE";

    String NEWS_ITEM_TYPE = "NEWS_ITEM_TYPE";

    String NEWS_ITEM_PUBLISHED_DATE = "PUBLISHED_DATE";


}

package com.yackeen.yackeen.model.db;


import com.yackeen.yackeen.model.dto.NewsItem;
import com.yackeen.yackeen.model.dto.WeatherItem;

import java.util.List;

import io.reactivex.Observable;


public interface NewsDataDBRepo {

    void saveNYTimesTopStories(List<NewsItem> newItems);

    Observable<List<NewsItem>> getNYTimesTopStories();

    void saveForecast(List<WeatherItem> weatherItems);

    Observable<List<WeatherItem>> getForecast();

    void deleteWeatherDate();

    void deleteNewsDate();

}

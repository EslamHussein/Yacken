package com.yackeen.yackeen.model.db;


import com.yackeen.base.db.YSolutionDataBase;
import com.yackeen.base.repo.cloud.BaseCloudRepo;
import com.yackeen.yackeen.model.dto.NewsItem;
import com.yackeen.yackeen.model.dto.WeatherItem;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;


public class NewsDataDBRepoImpl extends BaseCloudRepo implements NewsDataDBRepo {


    private YSolutionDataBase db;


    public NewsDataDBRepoImpl(YSolutionDataBase db) {
        this.db = db;
    }

    @Override
    public void saveNYTimesTopStories(List<NewsItem> newItems) {

        db.newsItemDao().insertAll(newItems);


    }

    @Override
    public Observable<List<NewsItem>> getNYTimesTopStories() {
        return Observable.fromCallable(new Callable<List<NewsItem>>() {
            @Override
            public List<NewsItem> call() throws Exception {
                return db.newsItemDao().getAll();

            }
        });
    }

    @Override
    public void saveForecast(List<WeatherItem> weatherItems) {

        db.weatherDao().insertAll(weatherItems);

    }

    @Override
    public Observable<List<WeatherItem>> getForecast() {
        return Observable.fromCallable(new Callable<List<WeatherItem>>() {
            @Override
            public List<WeatherItem> call() throws Exception {

                return db.weatherDao().getAll();

            }
        });
    }

    @Override
    public void deleteWeatherDate() {

        db.weatherDao().deleteAll();


    }

    @Override
    public void deleteNewsDate() {
        db.newsItemDao().deleteAll();
    }
}

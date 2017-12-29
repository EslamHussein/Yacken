package com.yackeen.yackeen.model;

import android.location.Location;

import com.google.common.collect.Lists;
import com.yackeen.base.error.AppException;
import com.yackeen.base.error.ErrorManager;
import com.yackeen.utils.TextUtils;
import com.yackeen.yackeen.R;
import com.yackeen.yackeen.model.cloud.NewsDataCloudRepo;
import com.yackeen.yackeen.model.db.NewsDataDBRepo;
import com.yackeen.yackeen.model.dto.NewsItem;
import com.yackeen.yackeen.model.dto.WeatherItem;
import com.yackeen.yackeen.model.dto.cloud.ForecastResponse;
import com.yackeen.yackeen.model.dto.cloud.NYTimesTopStoriesResponse;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;


/**
 * Created by EslamHussein on 12/28/17.
 */

public class NewsDataRepImpl {

    private NewsDataCloudRepo newsDataCloudRepo;
    private NewsDataDBRepo newsDataDBRepo;


    public NewsDataRepImpl(NewsDataCloudRepo newsDataCloudRepo, NewsDataDBRepo newsDataDBRepo) {
        this.newsDataCloudRepo = newsDataCloudRepo;
        this.newsDataDBRepo = newsDataDBRepo;
    }


    public Observable<List<Object>> getData(Location location) {

        if (location == null) {
            return getDataOffline();
        }
        return Observable.mergeDelayError(getDataOnline(String.valueOf(location.getLatitude()), String.valueOf(location.getLongitude())), getDataOffline());

    }

    private Observable<List<Object>> getDataOnline(String lat, String lng) {


        Observable<List<NewsItem>> nyTimesTopStoriesResponseObservable = ErrorManager.wrap(newsDataCloudRepo.getNYTimesTopStories()).flatMap(new Function<NYTimesTopStoriesResponse, ObservableSource<List<NewsItem>>>() {
            @Override
            public ObservableSource<List<NewsItem>> apply(NYTimesTopStoriesResponse nyTimesTopStoriesResponse) throws Exception {
                if (nyTimesTopStoriesResponse == null || nyTimesTopStoriesResponse.getResults() == null
                        || nyTimesTopStoriesResponse.getResults().isEmpty() || nyTimesTopStoriesResponse.getNumResults() <= 0) {

                    return Observable.error(new AppException(AppException.NO_DATA_ERROR, TextUtils.getString(R.string.no_results_found)));
                }
                newsDataDBRepo.deleteNewsDate();
                newsDataDBRepo.saveNYTimesTopStories(nyTimesTopStoriesResponse.getResults());

                return Observable.just(nyTimesTopStoriesResponse.getResults());
            }
        });


        Observable<List<WeatherItem>> forecastResponseObservable = ErrorManager.wrap(newsDataCloudRepo.getForecast(lat, lng)).flatMap(new Function<ForecastResponse, ObservableSource<List<WeatherItem>>>() {
            @Override
            public ObservableSource<List<WeatherItem>> apply(ForecastResponse forecastResponse) throws Exception {


                if (forecastResponse == null || forecastResponse.getList() == null
                        || forecastResponse.getList().isEmpty() || forecastResponse.getCnt() <= 0) {

                    return Observable.error(new AppException(AppException.NO_DATA_ERROR, TextUtils.getString(R.string.no_results_found)));
                }
                newsDataDBRepo.deleteWeatherDate();
                newsDataDBRepo.saveForecast(forecastResponse.getList());

                return Observable.just(forecastResponse.getList());
            }
        });


        return zipNewsAndWeatherDate(nyTimesTopStoriesResponseObservable, forecastResponseObservable);


    }


    private Observable<List<Object>> zipNewsAndWeatherDate(Observable<List<NewsItem>> nyTimesTopStoriesResponseObservable, Observable<List<WeatherItem>> forecastResponseObservable) {


        return Observable.zip(nyTimesTopStoriesResponseObservable, forecastResponseObservable, new BiFunction<List<NewsItem>, List<WeatherItem>, List<Object>>() {
            @Override
            public List<Object> apply(List<NewsItem> newsItems, List<WeatherItem> weatherItems) throws Exception {

                List<List<NewsItem>> smallerNewsItems = Lists.partition(newsItems, 5);

                List<List<WeatherItem>> smallerItems = Lists.partition(weatherItems, 5);

                List<Object> objects = new ArrayList<>();

                for (int i = 0; i < smallerNewsItems.size(); i++) {

                    objects.addAll(smallerNewsItems.get(i));
                    if (smallerItems.size() >= i && smallerItems.size() > i)
                        objects.add(smallerItems.get(i));

                }

                return objects;


            }
        });

    }


    public Observable<List<Object>> getDataOffline() {


        Observable<List<NewsItem>> newsListObservable = ErrorManager.wrap(newsDataDBRepo.getNYTimesTopStories()).flatMap(new Function<List<NewsItem>, ObservableSource<List<NewsItem>>>() {
            @Override
            public ObservableSource<List<NewsItem>> apply(List<NewsItem> newsItems) throws Exception {
                if (newsItems == null || newsItems.isEmpty() || newsItems.size() <= 0) {

                    return Observable.error(new AppException(AppException.NO_DATA_ERROR, TextUtils.getString(R.string.no_results_found)));
                }


                return Observable.just(newsItems);
            }
        });


        Observable<List<WeatherItem>> weatherListObservable = ErrorManager.wrap(newsDataDBRepo.getForecast()).flatMap(new Function<List<WeatherItem>, ObservableSource<List<WeatherItem>>>() {
            @Override
            public ObservableSource<List<WeatherItem>> apply(List<WeatherItem> weatherItems) throws Exception {


                if (weatherItems == null || weatherItems.isEmpty() || weatherItems.size() <= 0) {

                    return Observable.error(new AppException(AppException.NO_DATA_ERROR, TextUtils.getString(R.string.no_results_found)));
                }

                return Observable.just(weatherItems);
            }
        });
        return zipNewsAndWeatherDate(newsListObservable, weatherListObservable);

    }


}

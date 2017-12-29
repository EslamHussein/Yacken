package com.yackeen.base.repo.cloud;


import com.yackeen.yackeen.model.dto.cloud.ForecastResponse;
import com.yackeen.yackeen.model.dto.cloud.NYTimesTopStoriesResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by EslamHussein on 12/28/17.
 */

public interface AppServices {


    @GET("topstories/v2/home.json")
    Observable<NYTimesTopStoriesResponse> getNYTimesTopStories(@Query("api-key") String apiKey);

    @GET("forecast")
    Observable<ForecastResponse> getForecast(@Query("lat") String lat, @Query("lon") String lng, @Query("appid") String appId);
}

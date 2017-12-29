package com.yackeen.yackeen.model.cloud;


import com.yackeen.yackeen.model.dto.cloud.ForecastResponse;
import com.yackeen.yackeen.model.dto.cloud.NYTimesTopStoriesResponse;

import io.reactivex.Observable;

public interface NewsDataCloudRepo {
    Observable<NYTimesTopStoriesResponse> getNYTimesTopStories();

    Observable<ForecastResponse> getForecast(String lat, String lng);

}

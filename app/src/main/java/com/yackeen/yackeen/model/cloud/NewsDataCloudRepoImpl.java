package com.yackeen.yackeen.model.cloud;


import com.yackeen.base.repo.cloud.AppServices;
import com.yackeen.base.repo.cloud.BaseCloudRepo;
import com.yackeen.base.repo.cloud.CloudConfig;
import com.yackeen.yackeen.model.dto.cloud.ForecastResponse;
import com.yackeen.yackeen.model.dto.cloud.NYTimesTopStoriesResponse;

import io.reactivex.Observable;


public class NewsDataCloudRepoImpl extends BaseCloudRepo implements NewsDataCloudRepo {

    @Override
    public Observable<NYTimesTopStoriesResponse> getNYTimesTopStories() {
        return create(AppServices.class, CloudConfig.NYTimes_BASE_URL).getNYTimesTopStories(CloudConfig.NYTimes_API_Key);
    }

    @Override
    public Observable<ForecastResponse> getForecast(String lat, String lng) {
        return create(AppServices.class, CloudConfig.OPEN_WEATHER_MAP_BASE_URL).getForecast(lat, lng, CloudConfig.OPEN_WEATHER_MAP_API_KEY);
    }
}

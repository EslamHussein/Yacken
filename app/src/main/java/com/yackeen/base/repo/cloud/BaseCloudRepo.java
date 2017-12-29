package com.yackeen.base.repo.cloud;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.yackeen.base.json.NewsItemDeserializer;
import com.yackeen.yackeen.model.dto.cloud.NYTimesTopStoriesResponse;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseCloudRepo {

    protected <T> T create(Class<T> clazz, String baseUrl) {
        T service = retrofit(baseUrl).create(clazz);
        return service;
    }

    private Retrofit retrofit(String baseUrl) {
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .build();

        GsonBuilder gsonBuilder = new GsonBuilder();

        gsonBuilder.registerTypeAdapter(NYTimesTopStoriesResponse.class, new NewsItemDeserializer());

        Gson customGson = gsonBuilder.create();

        return new Retrofit.Builder()
                .baseUrl(baseUrl).client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(customGson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }


}

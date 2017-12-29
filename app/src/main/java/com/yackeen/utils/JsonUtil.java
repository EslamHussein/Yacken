package com.yackeen.utils;

import com.google.gson.Gson;

/**
 * Created by EslamHussein on 12/29/17.
 */

public class JsonUtil {

    public static <T> T parseJson(String jsonString, Class<T> clazz) {

        try {
            Gson gson = new Gson();
            return gson.fromJson(jsonString, clazz);

        } catch (Throwable e) {
            return null;
        }

    }

}

package com.yackeen.base.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.yackeen.utils.JsonUtil;
import com.yackeen.yackeen.model.dto.MultiMediaItem;
import com.yackeen.yackeen.model.dto.cloud.NYTimesTopStoriesResponse;

import java.lang.reflect.Type;

/**
 * Created by EslamHussein on 12/29/17.
 */

public class NewsItemDeserializer implements JsonDeserializer<NYTimesTopStoriesResponse> {
    @Override
    public NYTimesTopStoriesResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        JsonObject jobject = json.getAsJsonObject();


        NYTimesTopStoriesResponse nyTimesTopStoriesResponse = JsonUtil.parseJson(jobject.toString(), NYTimesTopStoriesResponse.class);

        for (int i = 0; i < nyTimesTopStoriesResponse.getResults().size(); i++) {


            JsonArray multiMediaJsonArray = jobject.get("results").getAsJsonArray().get(i).getAsJsonObject().get("multimedia").getAsJsonArray();

            JsonObject multiMediaJsonObject = null;
            if (multiMediaJsonArray != null && multiMediaJsonArray.size() > 0) {

                multiMediaJsonObject = multiMediaJsonArray.get(0).getAsJsonObject();
            }
            MultiMediaItem multiMediaItem = null;
            if (multiMediaJsonObject != null)

            {

                multiMediaItem = new MultiMediaItem(multiMediaJsonObject.get("url").getAsString(),
                        multiMediaJsonObject.get("height").getAsInt(), multiMediaJsonObject.get("width").getAsInt());

                nyTimesTopStoriesResponse.getResults().get(i).setMultiMediaItem(multiMediaItem);
            }


        }


        return nyTimesTopStoriesResponse;


    }
}
